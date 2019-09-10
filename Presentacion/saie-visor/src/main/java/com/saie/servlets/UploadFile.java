/*     */ package com.saie.servlets;
/*     */ 
/*     */ import com.iecisa.sat.saie.response.ServiceResponse;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.CatalogoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosDuplicidadDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ReporteBiometristaDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import com.itextpdf.text.Font;
/*     */ import com.itextpdf.text.FontFactory;
/*     */ import com.itextpdf.text.Image;
/*     */ import com.itextpdf.text.Phrase;
/*     */ import com.itextpdf.text.Rectangle;
/*     */ import com.itextpdf.text.pdf.ColumnText;
/*     */ import com.itextpdf.text.pdf.PdfContentByte;
/*     */ import com.itextpdf.text.pdf.PdfReader;
/*     */ import com.itextpdf.text.pdf.PdfStamper;
/*     */ import com.saie.servlets.UploadFile;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.math.BigInteger;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.security.cert.X509Certificate;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.glxn.qrgen.core.image.ImageType;
/*     */ import net.glxn.qrgen.javase.QRCode;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UploadFile
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*  53 */     if (ServletFileUpload.isMultipartContent(req)) {
/*  54 */       DiskFileItemFactory factory = new DiskFileItemFactory();
/*  55 */       factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
/*     */       
/*  57 */       ServletFileUpload upload = new ServletFileUpload(factory);
/*     */       
/*  59 */       String uploadPath = System.getProperty("SAIE_BIOREPORTES_ROOT");
/*  60 */       String firmaB64 = null;
/*  61 */       InputStream certificado = null;
/*  62 */       InputStream llave = null;
/*  63 */       String pass = "";
/*  64 */       String rfccer = "";
/*  65 */       String caso = "";
/*  66 */       String rfc1 = "";
/*  67 */       String rfc2 = "";
/*  68 */       String resolucion = "";
/*  69 */       String observaciones = "";
/*  70 */       int casoSat = 0;
/*  71 */       FileItem archivoTemp = null;
/*     */       
/*     */       try {
/*  74 */         List<FileItem> formItems = upload.parseRequest(req);
/*     */         
/*  76 */         if (formItems != null && formItems.size() > 0) {
/*  77 */           for (FileItem item : formItems) {
/*  78 */             if (!item.isFormField()) {
/*  79 */               if (item.getName().contains(".cer")) {
/*  80 */                 certificado = item.getInputStream(); continue;
/*  81 */               }  if (item.getName().contains(".key")) {
/*  82 */                 llave = item.getInputStream(); continue;
/*     */               } 
/*  84 */               archivoTemp = item;
/*     */               continue;
/*     */             } 
/*  87 */             if (item.getFieldName().equals("claveFiel")) {
/*  88 */               pass = item.getString(); continue;
/*  89 */             }  if (item.getFieldName().equals("usuarioFiel")) {
/*  90 */               rfccer = item.getString(); continue;
/*  91 */             }  if (item.getFieldName().equals("fcaso")) {
/*  92 */               caso = item.getString(); continue;
/*  93 */             }  if (item.getFieldName().equals("frfc1")) {
/*  94 */               rfc1 = item.getString(); continue;
/*  95 */             }  if (item.getFieldName().equals("frfc2")) {
/*  96 */               rfc2 = item.getString(); continue;
/*  97 */             }  if (item.getFieldName().equals("resolucion")) {
/*  98 */               resolucion = item.getString(); continue;
/*  99 */             }  if (item.getFieldName().equals("observaciones")) {
/* 100 */               observaciones = item.getString(); continue;
/* 101 */             }  if (item.getFieldName().equals("casoSat")) {
/* 102 */               casoSat = Integer.parseInt(item.getString());
/*     */             }
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 108 */         UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/* 109 */         X509Certificate cer = X509Certificate.getInstance(certificado);
/* 110 */         BigInteger serial = new BigInteger(cer.getSerialNumber().toString());
/* 111 */         SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss");
/* 112 */         String fecha = sdf.format(new Date());
/* 113 */         String curp = cer.getSubjectDN().getName().substring(cer.getSubjectDN().getName().indexOf("SERIALNUMBER=") + 13, cer.getSubjectDN().getName().indexOf("SERIALNUMBER=") + 31);
/* 114 */         String idsat = "700-001";
/* 115 */         String digestion = null;
/* 116 */         String algoritmo = "SHA-256";
/*     */         
/* 118 */         DatosDuplicidadDTO duplicadodto = null;
/* 119 */         List<DatosDuplicidadDTO> duplicadosdtos = (List)getServletContext().getAttribute("duplicadosDTO");
/* 120 */         for (DatosDuplicidadDTO duplicadoAuxiliar : duplicadosdtos) {
/* 121 */           if (duplicadoAuxiliar.getCaso_numero_caso().equals(caso)) {
/* 122 */             duplicadodto = duplicadoAuxiliar;
/* 123 */             if (duplicadodto.getReporte_url() != null && !duplicadodto.getReporte_url().trim().isEmpty()) {
/* 124 */               resp.sendRedirect("duplicado_tablas.jsp");
/*     */               
/*     */               return;
/*     */             } 
/*     */             break;
/*     */           } 
/*     */         } 
/* 131 */         try (InputStream inputStream = archivoTemp.getInputStream()) {
/* 132 */           MessageDigest digest = MessageDigest.getInstance("SHA-256");
/*     */           
/* 134 */           byte[] bytesBuffer = new byte[1024];
/* 135 */           int bytesRead = -1;
/*     */           
/* 137 */           while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
/* 138 */             digest.update(bytesBuffer, 0, bytesRead);
/*     */           }
/*     */           
/* 141 */           byte[] hashedBytes = digest.digest();
/*     */           
/* 143 */           StringBuffer stringBuffer = new StringBuffer();
/* 144 */           for (int i = 0; i < hashedBytes.length; i++) {
/* 145 */             stringBuffer.append(Integer.toString((hashedBytes[i] & 0xFF) + 256, 16).substring(1));
/*     */           }
/* 147 */           digestion = stringBuffer.toString();
/* 148 */         } catch (NoSuchAlgorithmException|IOException e) {
/* 149 */           e.printStackTrace();
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 155 */         String textoPorFirmar = "||" + serial + "|" + fecha + "|" + usuario.getRfc() + "|" + curp + "|" + idsat + "|" + caso + "|" + digestion + "|" + algoritmo + "|" + rfc1 + "|" + rfc2 + "|" + duplicadodto.getFecha_enrolamiento().replace(" ", "T") + "|" + duplicadodto.getFecha_enrolamiento2().replace(" ", "T") + "|" + duplicadodto.getLocalidad_enrolamiento() + "|" + duplicadodto.getLocalidad_enrolamiento2() + "|" + (duplicadodto.getScore().equals("") ? "0" : duplicadodto.getScore()) + "||";
/* 156 */         if (certificado != null && llave != null) {
/* 157 */           certificado.reset();
/* 158 */           ServiceResponse<String> responseFiel = (new ServiceEnrolamiento()).validarFirmarFiel(certificado, llave, pass, textoPorFirmar, usuario.getRfc());
/* 159 */           if (responseFiel.getCode() == -1) {
/* 160 */             req.setAttribute("mensaje", responseFiel.getMessage());
/*     */           } else {
/* 162 */             firmaB64 = (String)responseFiel.getResult();
/*     */           } 
/*     */         } 
/* 165 */         System.out.println("cadena: " + textoPorFirmar);
/* 166 */         System.out.println("firma: " + firmaB64);
/*     */         
/* 168 */         if (req.getAttribute("mensaje") == null) {
/* 169 */           String fileName = caso + "-" + rfc1 + "-" + rfc2 + ".pdf";
/* 170 */           String filePath = uploadPath + fileName;
/*     */ 
/*     */           
/* 173 */           PdfReader pdfr = new PdfReader(archivoTemp.get());
/* 174 */           PdfStamper pdfs = new PdfStamper(pdfr, new FileOutputStream(filePath));
/* 175 */           PdfContentByte pdfc = pdfs.getOverContent(pdfr.getNumberOfPages());
/* 176 */           Rectangle bounds = pdfr.getPageSize(pdfr.getNumberOfPages());
/*     */           
/* 178 */           String uuid = UUID.randomUUID().toString();
/* 179 */           ByteArrayOutputStream qr = QRCode.from("http://serviciosinternos.sat.gob.mx/fiel/visor/duplicado_reporte.jsp?casouid=" + uuid).to(ImageType.JPG).stream();
/* 180 */           Image img = Image.getInstance(qr.toByteArray());
/* 181 */           img.setAbsolutePosition(bounds.getWidth() - 150.0F, 88.0F);
/* 182 */           pdfc.addImage(img);
/*     */           
/* 184 */           Font fuente = FontFactory.getFont("Noto-Sans", 8.0F);
/* 185 */           Font fuenteBold = FontFactory.getFont("Noto-Sans", 8.0F, 1);
/*     */           
/* 187 */           ColumnText ct = new ColumnText(pdfc);
/* 188 */           String firma = "Cadena original de solicitud:";
/* 189 */           Phrase texto = new Phrase(firma, fuenteBold);
/* 190 */           ct.setSimpleColumn(texto, 35.0F, 10.0F, bounds.getWidth() - 150.0F, 200.0F, 15.0F, 0);
/* 191 */           ct.go();
/*     */           
/* 193 */           ct = new ColumnText(pdfc);
/* 194 */           firma = textoPorFirmar;
/* 195 */           String firmaAux = firma;
/* 196 */           String firmaFinal = "";
/* 197 */           int posy = 190;
/* 198 */           for (int i = firmaAux.length(); i > 0; i--) {
/* 199 */             if (fuente.getCalculatedBaseFont(true).getWidthPoint(firmaAux.substring(0, i), fuente.getSize()) <= bounds.getWidth() - 150.0F - 160.0F) {
/* 200 */               firmaFinal = firmaFinal + firmaAux.substring(0, i);
/* 201 */               firmaAux = firmaAux.substring(i);
/* 202 */               posy -= 15;
/* 203 */               if (i == firmaAux.length()) {
/*     */                 break;
/*     */               }
/* 206 */               i = firmaAux.length() + 1;
/* 207 */               firmaFinal = firmaFinal + "\n";
/*     */             } 
/*     */           } 
/*     */           
/* 211 */           texto = new Phrase(firmaFinal, fuente);
/* 212 */           ct.setSimpleColumn(texto, 160.0F, 10.0F, bounds.getWidth() - 150.0F, 200.0F, 15.0F, 0);
/* 213 */           ct.go();
/*     */           
/* 215 */           ct = new ColumnText(pdfc);
/* 216 */           firma = "Firma digital:";
/* 217 */           texto = new Phrase(firma, fuenteBold);
/* 218 */           ct.setSimpleColumn(texto, 35.0F, 10.0F, bounds.getWidth() - 150.0F, posy, 15.0F, 0);
/* 219 */           ct.go();
/*     */           
/* 221 */           ct = new ColumnText(pdfc);
/* 222 */           firma = firmaB64;
/* 223 */           texto = new Phrase(firma, fuente);
/* 224 */           ct.setSimpleColumn(texto, 160.0F, 10.0F, bounds.getWidth() - 150.0F, posy, 15.0F, 0);
/* 225 */           ct.go();
/*     */           
/* 227 */           ct = new ColumnText(pdfc);
/* 228 */           firma = "Código QR para confirmación con SAIE";
/* 229 */           texto = new Phrase(firma, fuente);
/* 230 */           ct.setSimpleColumn(texto, bounds.getWidth() - 135.0F, 10.0F, bounds.getWidth() - 35.0F, 110.0F, 15.0F, 1);
/* 231 */           ct.go();
/*     */           
/* 233 */           pdfs.close();
/*     */ 
/*     */           
/* 236 */           List<CatalogoDTO> catalogo = (List)getServletContext().getAttribute("catalogoTipoResolucion");
/* 237 */           String resolucionValue = null;
/* 238 */           System.out.println("resolucion id: " + resolucion);
/* 239 */           for (CatalogoDTO cat : catalogo) {
/* 240 */             if (cat.getId() == Integer.parseInt(resolucion)) {
/* 241 */               resolucionValue = cat.getValor();
/*     */               break;
/*     */             } 
/*     */           } 
/* 245 */           catalogo = (List)getServletContext().getAttribute("catalogoTipoObservacion");
/* 246 */           String observacionesValue = null;
/* 247 */           for (CatalogoDTO cat : catalogo) {
/* 248 */             if (cat.getId() == Integer.parseInt(observaciones)) {
/* 249 */               observacionesValue = cat.getValor();
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 254 */           ReporteBiometristaDTO reportedto = new ReporteBiometristaDTO(caso, rfc1, rfc2, fileName, rfccer, firmaB64, resolucion, observaciones, resolucionValue, observacionesValue, uuid, textoPorFirmar, casoSat, duplicadodto.getEpid(), duplicadodto.getTipoCaso());
/* 255 */           reportedto.setOperador(usuario.getRfc());
/* 256 */           ServiceResponse<String> response = (new ServiceEnrolamiento()).setReporteBiometristaDuplicados(reportedto, usuario, duplicadodto.getEpid());
/* 257 */           if (response.getCode() == 0) {
/* 258 */             req.setAttribute("mensaje", "Reporte subido correctamente");
/* 259 */             duplicadodto.setResolucion(resolucionValue);
/* 260 */             duplicadodto.setObservaciones(observacionesValue);
/* 261 */             duplicadodto.setReporte_url(fileName);
/* 262 */             duplicadodto.setIdReporte(uuid);
/* 263 */             duplicadodto.setCasoSat(Integer.valueOf(casoSat));
/*     */           } else {
/* 265 */             req.setAttribute("mensaje", "Ocurrió un error en la conexión, por favor inténtalo nuevamente");
/*     */           } 
/*     */         } 
/* 268 */       } catch (Exception e) {
/* 269 */         e.printStackTrace();
/*     */       } 
/* 271 */       getServletContext().getRequestDispatcher("/duplicado_tablas.jsp").forward(req, resp);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\UploadFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */