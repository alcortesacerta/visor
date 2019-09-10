/*     */ package com.saie.servlets;
/*     */ 
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ArchivoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoAdjuntosDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*  23 */     String opcion = req.getPathInfo();
/*  24 */     opcion = req.getParameter("tipo");
/*     */     
/*  26 */     String duplicidad = (req.getParameter("duplicidad") != null) ? req.getParameter("duplicidad") : "";
/*     */     
/*  28 */     byte[] imagen = null;
/*  29 */     String estado = "";
/*  30 */     ArchivoDTO archivodtoTemp = null;
/*  31 */     if (opcion.equals("foto")) {
/*  32 */       VersionEnrolamientoGeneralDTO versionDto = (VersionEnrolamientoGeneralDTO)req.getSession().getAttribute("versionEnrolamiento" + duplicidad);
/*  33 */       archivodtoTemp = versionDto.getFoto();
/*  34 */       if (archivodtoTemp != null) {
/*  35 */         resp.setContentType(versionDto.getFoto().getDataContentType());
/*  36 */         imagen = versionDto.getFoto().getData();
/*     */       } 
/*  38 */     } else if (opcion.equals("firma")) {
/*  39 */       VersionEnrolamientoGeneralDTO versionDto = (VersionEnrolamientoGeneralDTO)req.getSession().getAttribute("versionEnrolamiento");
/*  40 */       archivodtoTemp = versionDto.getFirma();
/*  41 */       if (archivodtoTemp != null) {
/*  42 */         resp.setContentType(versionDto.getFirma().getDataContentType());
/*  43 */         imagen = versionDto.getFirma().getData();
/*     */       } 
/*  45 */     } else if (opcion.equals("huella")) {
/*  46 */       VersionEnrolamientoAdjuntosDTO adjuntosDto = (VersionEnrolamientoAdjuntosDTO)req.getSession().getAttribute("adjuntosDto" + duplicidad);
/*  47 */       String parametro = req.getParameter("imagen");
/*  48 */       for (int i = 0; i < adjuntosDto.getHuellas().size(); i++) {
/*  49 */         if (((ArchivoDTO)adjuntosDto.getHuellas().get(i)).getId().equals(parametro)) {
/*  50 */           if (((ArchivoDTO)adjuntosDto.getHuellas().get(i)).getName().equals("amputado")) {
/*  51 */             estado = "amputado";
/*  52 */           } else if (((ArchivoDTO)adjuntosDto.getHuellas().get(i)).getName().equals("vendado")) {
/*  53 */             estado = "vendado";
/*  54 */           } else if (((ArchivoDTO)adjuntosDto.getHuellas().get(i)).getName().equals("noaplica")) {
/*  55 */             estado = "noaplica";
/*     */           } else {
/*  57 */             resp.setContentType(((ArchivoDTO)adjuntosDto.getHuellas().get(i)).getDataContentType());
/*  58 */             imagen = ((ArchivoDTO)adjuntosDto.getHuellas().get(i)).getData();
/*     */           } 
/*     */         }
/*     */       } 
/*  62 */     } else if (opcion.equals("iris")) {
/*  63 */       VersionEnrolamientoAdjuntosDTO adjuntosDto = (VersionEnrolamientoAdjuntosDTO)req.getSession().getAttribute("adjuntosDto" + duplicidad);
/*  64 */       String parametro = req.getParameter("imagen");
/*  65 */       for (int i = 0; i < adjuntosDto.getIris().size(); i++) {
/*  66 */         if (((ArchivoDTO)adjuntosDto.getIris().get(i)).getId().equals(parametro)) {
/*  67 */           if (((ArchivoDTO)adjuntosDto.getIris().get(i)).getName().equals("amputado")) {
/*  68 */             estado = "amputado";
/*  69 */           } else if (((ArchivoDTO)adjuntosDto.getIris().get(i)).getName().equals("vendado")) {
/*  70 */             estado = "vendado";
/*  71 */           } else if (((ArchivoDTO)adjuntosDto.getIris().get(i)).getName().equals("noaplica")) {
/*  72 */             estado = "noaplica";
/*     */           } else {
/*  74 */             resp.setContentType(((ArchivoDTO)adjuntosDto.getIris().get(i)).getDataContentType());
/*  75 */             imagen = ((ArchivoDTO)adjuntosDto.getIris().get(i)).getData();
/*     */           } 
/*     */         }
/*     */       } 
/*  79 */     } else if (opcion.equals("documento")) {
/*  80 */       VersionEnrolamientoAdjuntosDTO adjuntosDto = (VersionEnrolamientoAdjuntosDTO)req.getSession().getAttribute("adjuntosDto");
/*  81 */       String parametro = req.getParameter("imagen");
/*  82 */       for (int i = 0; i < adjuntosDto.getDocumentos().size(); i++) {
/*  83 */         if (((ArchivoDTO)adjuntosDto.getDocumentos().get(i)).getId().equals(parametro)) {
/*  84 */           if (((ArchivoDTO)adjuntosDto.getDocumentos().get(i)).getName().equals("noaplica")) {
/*  85 */             estado = "noaplica";
/*     */           } else {
/*  87 */             resp.setContentType(((ArchivoDTO)adjuntosDto.getDocumentos().get(i)).getDataContentType());
/*  88 */             imagen = ((ArchivoDTO)adjuntosDto.getDocumentos().get(i)).getData();
/*     */           } 
/*     */         }
/*     */       } 
/*  92 */     } else if (opcion.equals("certificacion")) {
/*  93 */       archivodtoTemp = (ArchivoDTO)req.getSession().getAttribute("certificacionPDF");
/*  94 */       imagen = archivodtoTemp.getData();
/*  95 */       resp.setContentType("application/force-download");
/*  96 */       resp.setContentLength(imagen.length);
/*  97 */       resp.setHeader("Content-Transfer-Encoding", "binary");
/*  98 */       resp.setHeader("Content-Disposition", "attachment; filename=\"" + ((DatosEnrolamientoDTO)req.getSession().getAttribute("enrolamientoDto")).getRfc() + "certificacion.pdf");
/*  99 */     } else if (opcion.equals("certificacionDictamen")) {
/* 100 */       archivodtoTemp = (ArchivoDTO)req.getSession().getAttribute("certificacionPDF");
/* 101 */       imagen = archivodtoTemp.getData();
/* 102 */       resp.setContentType("application/force-download");
/* 103 */       resp.setContentLength(imagen.length);
/* 104 */       resp.setHeader("Content-Transfer-Encoding", "binary");
/* 105 */       resp.setHeader("Content-Disposition", "attachment; filename=" + req.getParameter("caso") + "CertificacionDictamen.pdf");
/* 106 */     } else if (opcion.equals("descarga")) {
/* 107 */       archivodtoTemp = (ArchivoDTO)req.getSession().getAttribute("adjuntosZIP");
/* 108 */       imagen = archivodtoTemp.getData();
/* 109 */       resp.setContentType("application/force-download");
/* 110 */       resp.setContentLength(imagen.length);
/* 111 */       resp.setHeader("Content-Transfer-Encoding", "binary");
/* 112 */       resp.setHeader("Content-Disposition", "attachment; filename=\"" + ((DatosEnrolamientoDTO)req.getSession().getAttribute("enrolamientoDto")).getRfc() + "adjuntos.zip");
/* 113 */     } else if (opcion.equals("reporte")) {
/* 114 */       archivodtoTemp = (new ServiceEnrolamiento()).getReporteDuplicidadByte(req.getParameter("imagen"));
/* 115 */       imagen = archivodtoTemp.getData();
/* 116 */       resp.setContentType("application/force-download");
/* 117 */       resp.setContentLength(imagen.length);
/* 118 */       resp.setHeader("Content-Transfer-Encoding", "binary");
/* 119 */       resp.setHeader("Content-Disposition", "attachment; filename=\"" + req.getParameter("imagen"));
/*     */     } 
/* 121 */     resp.setHeader("Pragma", "no-cache");
/* 122 */     resp.setHeader("Cache-Control", "no-cache");
/* 123 */     resp.setHeader("Expires", "0");
/* 124 */     if (estado.equals("amputado")) {
/* 125 */       req.getRequestDispatcher("/imgs/amputado.png").forward(req, resp);
/* 126 */     } else if (estado.equals("vendado")) {
/* 127 */       req.getRequestDispatcher("/imgs/vendado.png").forward(req, resp);
/* 128 */     } else if (estado.equals("noaplica")) {
/* 129 */       req.getRequestDispatcher("/imgs/no_aplica.png").forward(req, resp);
/* 130 */     } else if (imagen == null) {
/* 131 */       req.getRequestDispatcher("/imgs/no_aplica.png").forward(req, resp);
/*     */     } else {
/* 133 */       ServletOutputStream servletOutputStream = resp.getOutputStream();
/* 134 */       servletOutputStream.write(imagen);
/* 135 */       servletOutputStream.flush();
/* 136 */       servletOutputStream.close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\ImageServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */