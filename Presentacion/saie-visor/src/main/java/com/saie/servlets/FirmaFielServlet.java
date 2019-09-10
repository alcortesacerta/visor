/*     */ package com.saie.servlets;
/*     */ 
/*     */ import com.iecisa.sat.saie.response.ServiceResponse;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ControlVersionesDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.EstatusValidacionAutorizacionDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.Permisos;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import com.saie.servlets.FirmaFielServlet;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FirmaFielServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*  33 */     EstatusValidacionAutorizacionDTO estatusDto = new EstatusValidacionAutorizacionDTO();
/*  34 */     VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO)req.getSession().getAttribute("versionEnrolamiento");
/*  35 */     DatosEnrolamientoDTO datosdto = (DatosEnrolamientoDTO)req.getSession().getAttribute("enrolamientoDto");
/*  36 */     UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/*  37 */     Integer estatus = null;
/*     */     
/*  39 */     if (ServletFileUpload.isMultipartContent(req)) {
/*  40 */       DiskFileItemFactory factory = new DiskFileItemFactory();
/*  41 */       factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
/*  42 */       ServletFileUpload upload = new ServletFileUpload(factory);
/*     */       
/*  44 */       InputStream certificado = null;
/*  45 */       InputStream llave = null;
/*  46 */       String pass = "";
/*  47 */       String comentarios = "";
/*  48 */       boolean bvalidar = true;
/*     */       try {
/*  50 */         List<FileItem> formItems = upload.parseRequest(req);
/*  51 */         if (formItems != null && formItems.size() > 0) {
/*  52 */           for (FileItem item : formItems) {
/*  53 */             if (!item.isFormField()) {
/*  54 */               if (item.getName().contains(".cer")) {
/*  55 */                 certificado = item.getInputStream(); continue;
/*  56 */               }  if (item.getName().contains(".key"))
/*  57 */                 llave = item.getInputStream(); 
/*     */               continue;
/*     */             } 
/*  60 */             if (item.getFieldName().equals("claveFiel")) {
/*  61 */               pass = item.getString(); continue;
/*  62 */             }  if (item.getFieldName().equals("fcomentarios")) {
/*  63 */               comentarios = item.getString(); continue;
/*  64 */             }  if (item.getFieldName().equals("bvalidar")) {
/*  65 */               bvalidar = item.getString().equals("true");
/*     */             }
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*  71 */         if (usuario.getPermisos().contains(Permisos.VALIDAR) && versiondto.getEstatus() == 3) {
/*  72 */           estatus = bvalidar ? new Integer(5) : new Integer(4);
/*  73 */         } else if (usuario.getPermisos().contains(Permisos.AUTORIZAR) && versiondto.getEstatus() == 5) {
/*  74 */           estatus = bvalidar ? new Integer(6) : new Integer(7);
/*     */         } 
/*  76 */         estatusDto.setComentarios(comentarios);
/*  77 */         estatusDto.setMotivoCancelacion("");
/*     */         
/*  79 */         String textoEstatus = (estatus.intValue() == 4 || estatus.intValue() == 7) ? "|CancelacionDeActualizacion" : ((estatus.intValue() == 5) ? "|ValidacionDeActualizacion" : "|AutorizacionDeActualizacion");
/*     */         
/*  81 */         String textoPorFirmar = "||" + usuario.getRfc() + "|" + datosdto.getRfc() + "|" + versiondto.getFechaEnrolamiento().replace(" ", "T") + "|" + versiondto.getLocalidadEnrolamiento() + "|" + versiondto.getTipoMovimiento();
/*  82 */         textoPorFirmar = textoPorFirmar + textoEstatus + "||";
/*  83 */         if (certificado != null && llave != null) {
/*  84 */           ServiceResponse<String> responseFiel = (new ServiceEnrolamiento()).validarFirmarFiel(certificado, llave, pass, textoPorFirmar, usuario.getRfc());
/*  85 */           if (responseFiel.getCode() == -1) {
/*  86 */             req.setAttribute("mensaje", responseFiel.getMessage());
/*     */           } else {
/*  88 */             estatusDto.setFirma((String)responseFiel.getResult());
/*  89 */             estatusDto.setCadenaOriginal(textoPorFirmar);
/*     */           } 
/*     */         } 
/*  92 */       } catch (Exception e) {
/*  93 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     if (req.getAttribute("mensaje") == null) {
/*  98 */       estatusDto.setEstatus_validacion_actualizacion(estatus);
/*  99 */       estatusDto.setVersion("" + versiondto.getVersion());
/* 100 */       estatusDto.setRfc(datosdto.getRfc());
/*     */       
/* 102 */       if (estatus != null && (usuario.getPermisos().contains(Permisos.VALIDAR) || usuario.getPermisos().contains(Permisos.AUTORIZAR))) {
/* 103 */         ServiceResponse<Integer> response = (new ServiceEnrolamiento()).setEstatusValidacionAutorizacion(estatusDto, usuario);
/* 104 */         List<ControlVersionesDTO> validaciones = (List)getServletContext().getAttribute("validacionesDTO");
/* 105 */         List<ControlVersionesDTO> autorizaciones = (List)getServletContext().getAttribute("autorizacionesDTO");
/* 106 */         if (response.getCode() == 0) {
/* 107 */           ControlVersionesDTO valAutAux = null;
/* 108 */           synchronized (validaciones) {
/* 109 */             if (estatus.intValue() == 5) {
/* 110 */               req.setAttribute("mensaje", "Validación/Autorización de actualización realizada correctamente");
/* 111 */               for (ControlVersionesDTO validacion : validaciones) {
/* 112 */                 if (validacion != null && validacion.getRfc().equals(estatusDto.getRfc()) && validacion.getVersion() == Integer.parseInt(estatusDto.getVersion())) {
/* 113 */                   valAutAux = validacion;
/* 114 */                   valAutAux.setEstatus(new Integer(5));
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 118 */               validaciones.remove(valAutAux);
/* 119 */               autorizaciones.add(valAutAux);
/* 120 */             } else if (estatus.intValue() == 6) {
/* 121 */               req.setAttribute("mensaje", "Validación/Autorización de actualización realizada correctamente");
/* 122 */               for (ControlVersionesDTO autorizacion : autorizaciones) {
/* 123 */                 if (autorizacion != null && autorizacion.getRfc().equals(estatusDto.getRfc()) && autorizacion.getVersion() == Integer.parseInt(estatusDto.getVersion())) {
/* 124 */                   valAutAux = autorizacion;
/* 125 */                   valAutAux.setEstatus(new Integer(6));
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 129 */               autorizaciones.remove(valAutAux);
/* 130 */             } else if (estatus.intValue() == 4) {
/* 131 */               req.setAttribute("mensaje", "Actualización cancelada");
/* 132 */               for (ControlVersionesDTO validacion : validaciones) {
/* 133 */                 if (validacion != null && validacion.getRfc().equals(estatusDto.getRfc()) && validacion.getVersion() == Integer.parseInt(estatusDto.getVersion())) {
/* 134 */                   valAutAux = validacion;
/* 135 */                   valAutAux.setEstatus(new Integer(4));
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 139 */               validaciones.remove(valAutAux);
/* 140 */             } else if (estatus.intValue() == 7) {
/* 141 */               req.setAttribute("mensaje", "Actualización cancelada");
/* 142 */               for (ControlVersionesDTO autorizacion : autorizaciones) {
/* 143 */                 if (autorizacion != null && autorizacion.getRfc().equals(estatusDto.getRfc()) && autorizacion.getVersion() == Integer.parseInt(estatusDto.getVersion())) {
/* 144 */                   valAutAux = autorizacion;
/* 145 */                   valAutAux.setEstatus(new Integer(7));
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 149 */               autorizaciones.remove(valAutAux);
/*     */             } else {
/* 151 */               req.setAttribute("mensaje", "Actualización cancelada");
/*     */             } 
/*     */           } 
/* 154 */           versiondto.setEstatus(estatus.intValue());
/*     */         } else {
/* 156 */           req.setAttribute("mensaje", "Ocurrió un error en el proceso de validación/autorización, por favor inténtalo nuevamente");
/*     */         } 
/*     */       } 
/*     */       
/* 160 */       if (req.getAttribute("mensaje") != null) {
/* 161 */         System.out.println(req.getAttribute("mensaje"));
/*     */       }
/*     */     } 
/* 164 */     getServletContext().getRequestDispatcher("/visor_documentos.jsp").forward(req, resp);
/*     */   }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\FirmaFielServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */