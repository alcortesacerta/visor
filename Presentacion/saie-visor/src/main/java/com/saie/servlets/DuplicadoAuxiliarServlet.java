/*     */ package com.saie.servlets;
/*     */ 
/*     */ import com.iecisa.sat.saie.response.ServiceResponse;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ArchivoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosDuplicidadDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoAdjuntosDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import com.saie.servlets.DuplicadoAuxiliarServlet;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ 
/*     */ public class DuplicadoAuxiliarServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*  25 */     ServiceEnrolamiento servicio = new ServiceEnrolamiento();
/*     */     
/*  27 */     if (req.getParameter("score") != null) {
/*  28 */       req.getSession().setAttribute("scoreDuplicidad", req.getParameter("score"));
/*     */     }
/*  30 */     String caso = null;
/*  31 */     String casouid = null;
/*  32 */     if (req.getParameter("caso") != null && !req.getServletPath().equals("/duplicado_reporte.jsp")) {
/*  33 */       caso = req.getParameter("caso");
/*  34 */     } else if (req.getServletPath().equals("/duplicado_reporte.jsp") && req.getParameter("casouid") != null) {
/*  35 */       casouid = req.getParameter("casouid");
/*     */     } 
/*     */     
/*  38 */     DatosDuplicidadDTO duplicadodto = (DatosDuplicidadDTO)req.getSession().getAttribute("duplicadoDto");
/*  39 */     if (duplicadodto == null || !duplicadodto.getCaso_numero_caso().equals(caso)) {
/*     */ 
/*     */       
/*  42 */       req.getSession().removeAttribute("duplicadoDto");
/*  43 */       duplicadodto = null;
/*     */     } 
/*     */     
/*  46 */     if ((caso != null || casouid != null) && duplicadodto == null) {
/*     */       
/*  48 */       List<DatosDuplicidadDTO> duplicadosdtos = (List)getServletContext().getAttribute("duplicadosDTO");
/*  49 */       for (DatosDuplicidadDTO duplicadoAuxiliar : duplicadosdtos) {
/*  50 */         if (duplicadoAuxiliar.getCaso_numero_caso().equals(caso) || duplicadoAuxiliar.getIdReporte().equals(casouid)) {
/*  51 */           duplicadodto = duplicadoAuxiliar;
/*     */           break;
/*     */         } 
/*     */       } 
/*  55 */       if (duplicadodto != null) {
/*  56 */         req.getSession().setAttribute("duplicadoDto", duplicadodto);
/*     */       }
/*     */     } 
/*     */     
/*  60 */     ServiceResponse<VersionEnrolamientoGeneralDTO> result = servicio.getVersionUnoByRFC(duplicadodto.getRfc());
/*  61 */     if (result.getCode() == 0 && result.getResult() != null) {
/*  62 */       VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO)result.getResult();
/*  63 */       req.getSession().setAttribute("versionEnrolamiento1", versiondto);
/*  64 */       if (req.getServletPath().equals("/duplicado_rostro.jsp") || req.getServletPath().equals("/duplicado_reporte.jsp")) {
/*  65 */         servicio.getVersionFotoFirma(versiondto, duplicadodto.getRfc());
/*  66 */       } else if (req.getServletPath().equals("/duplicado_iris.jsp")) {
/*  67 */         ServiceResponse<List<ArchivoDTO>> sriris = servicio.getIris(versiondto, duplicadodto.getRfc());
/*  68 */         VersionEnrolamientoAdjuntosDTO adjuntosDto = new VersionEnrolamientoAdjuntosDTO();
/*  69 */         adjuntosDto.setIris((List)sriris.getResult());
/*  70 */         req.getSession().setAttribute("adjuntosDto1", adjuntosDto);
/*  71 */       } else if (req.getServletPath().equals("/duplicado_huella.jsp")) {
/*  72 */         ServiceResponse<List<ArchivoDTO>> srhuellas = servicio.getHuellas(versiondto, duplicadodto.getRfc());
/*  73 */         VersionEnrolamientoAdjuntosDTO adjuntosDto = new VersionEnrolamientoAdjuntosDTO();
/*  74 */         adjuntosDto.setHuellas((List)srhuellas.getResult());
/*  75 */         req.getSession().setAttribute("adjuntosDto1", adjuntosDto);
/*     */       } 
/*     */     } else {
/*  78 */       VersionEnrolamientoGeneralDTO versiondto = new VersionEnrolamientoGeneralDTO();
/*  79 */       req.getSession().setAttribute("versionEnrolamiento1", versiondto);
/*  80 */       VersionEnrolamientoAdjuntosDTO adjuntosDto = new VersionEnrolamientoAdjuntosDTO();
/*  81 */       adjuntosDto.setHuellas(new ArrayList());
/*  82 */       adjuntosDto.setIris(new ArrayList());
/*  83 */       req.getSession().setAttribute("adjuntosDto1", adjuntosDto);
/*     */     } 
/*     */     
/*  86 */     result = servicio.getVersionUnoByRFC(duplicadodto.getRfc2());
/*  87 */     if (result.getCode() == 0 && result.getResult() != null) {
/*  88 */       VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO)result.getResult();
/*  89 */       req.getSession().setAttribute("versionEnrolamiento2", versiondto);
/*  90 */       if (req.getServletPath().equals("/duplicado_rostro.jsp") || req.getServletPath().equals("/duplicado_reporte.jsp")) {
/*  91 */         servicio.getVersionFotoFirma(versiondto, duplicadodto.getRfc2());
/*  92 */       } else if (req.getServletPath().equals("/duplicado_iris.jsp")) {
/*  93 */         ServiceResponse<List<ArchivoDTO>> sriris = servicio.getIris(versiondto, duplicadodto.getRfc2());
/*  94 */         VersionEnrolamientoAdjuntosDTO adjuntosDto = new VersionEnrolamientoAdjuntosDTO();
/*  95 */         adjuntosDto.setIris((List)sriris.getResult());
/*  96 */         req.getSession().setAttribute("adjuntosDto2", adjuntosDto);
/*  97 */       } else if (req.getServletPath().equals("/duplicado_huella.jsp")) {
/*  98 */         ServiceResponse<List<ArchivoDTO>> srhuellas = servicio.getHuellas(versiondto, duplicadodto.getRfc2());
/*  99 */         VersionEnrolamientoAdjuntosDTO adjuntosDto = new VersionEnrolamientoAdjuntosDTO();
/* 100 */         adjuntosDto.setHuellas((List)srhuellas.getResult());
/* 101 */         req.getSession().setAttribute("adjuntosDto2", adjuntosDto);
/*     */       } 
/*     */     } else {
/* 104 */       VersionEnrolamientoGeneralDTO versiondto = new VersionEnrolamientoGeneralDTO();
/* 105 */       req.getSession().setAttribute("versionEnrolamiento2", versiondto);
/* 106 */       VersionEnrolamientoAdjuntosDTO adjuntosDto = new VersionEnrolamientoAdjuntosDTO();
/* 107 */       adjuntosDto.setHuellas(new ArrayList());
/* 108 */       adjuntosDto.setIris(new ArrayList());
/* 109 */       req.getSession().setAttribute("adjuntosDto2", adjuntosDto);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\DuplicadoAuxiliarServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */