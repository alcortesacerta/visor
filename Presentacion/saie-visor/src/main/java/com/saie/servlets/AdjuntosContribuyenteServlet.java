/*    */ package com.saie.servlets;
/*    */ 
/*    */ import com.iecisa.sat.saie.response.ServiceResponse;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.ArchivoDTO;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoAdjuntosDTO;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*    */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*    */ import com.saie.servlets.AdjuntosContribuyenteServlet;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ public class AdjuntosContribuyenteServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 22 */   private ServiceEnrolamiento servicio = new ServiceEnrolamiento();
/*    */ 
/*    */   
/*    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 26 */     VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO)req.getSession().getAttribute("versionEnrolamiento");
/* 27 */     DatosEnrolamientoDTO enrolamientoDto = (DatosEnrolamientoDTO)req.getSession().getAttribute("enrolamientoDto");
/* 28 */     if (enrolamientoDto == null || versiondto == null) {
/*    */       return;
/*    */     }
/* 31 */     VersionEnrolamientoAdjuntosDTO adjuntosDto = (VersionEnrolamientoAdjuntosDTO)req.getSession().getAttribute("adjuntosDto");
/* 32 */     if (adjuntosDto == null) {
/* 33 */       adjuntosDto = new VersionEnrolamientoAdjuntosDTO();
/*    */     }
/* 35 */     if (req.getServletPath().contains("/visor_huella") && adjuntosDto.getHuellas() == null) {
/* 36 */       ServiceResponse<List<ArchivoDTO>> serviceResponse = this.servicio.getHuellas(versiondto, enrolamientoDto.getRfc());
/* 37 */       adjuntosDto.setHuellas((List)serviceResponse.getResult());
/* 38 */     } else if (req.getServletPath().contains("/visor_iris") && adjuntosDto.getIris() == null) {
/* 39 */       ServiceResponse<List<ArchivoDTO>> serviceResponse = this.servicio.getIris(versiondto, enrolamientoDto.getRfc());
/* 40 */       adjuntosDto.setIris((List)serviceResponse.getResult());
/* 41 */     } else if (adjuntosDto.getDocumentos() == null) {
/* 42 */       ServiceResponse<List<ArchivoDTO>> serviceResponse = this.servicio.getDocumentos(versiondto, enrolamientoDto.getRfc());
/* 43 */       adjuntosDto.setDocumentos((List)serviceResponse.getResult());
/*    */     } 
/* 45 */     req.getSession().setAttribute("adjuntosDto", adjuntosDto);
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\AdjuntosContribuyenteServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */