package com.saie.servlets;
/*    */ 
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.ControlVersionesDTO;
/*    */ import com.saie.servlets.ActualizaValidacionesServlet;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActualizaValidacionesServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 20 */     System.out.println("borrando de contexto");
/*    */     try {
/* 22 */       String rfcBorrar = req.getParameter("rfc");
/* 23 */       int version = Integer.parseInt(req.getParameter("version"));
/* 24 */       List<ControlVersionesDTO> validaciones = (List)getServletContext().getAttribute("validacionesDTO");
/* 25 */       List<ControlVersionesDTO> autorizaciones = (List)getServletContext().getAttribute("autorizacionesDTO");
/* 26 */       ControlVersionesDTO versionTemporal = null;
/*    */       
/* 28 */       synchronized (validaciones) {
/* 29 */         for (ControlVersionesDTO versionBorrar : validaciones) {
/* 30 */           if (versionBorrar != null && versionBorrar.getRfc().equals(rfcBorrar) && versionBorrar.getVersion() == version) {
/* 31 */             versionTemporal = versionBorrar;
/*    */             break;
/*    */           } 
/*    */         } 
/* 35 */         if (versionTemporal != null) {
/* 36 */           validaciones.remove(versionTemporal);
/*    */         }
/*    */         
/* 39 */         for (ControlVersionesDTO versionBorrar : autorizaciones) {
/* 40 */           if (versionBorrar != null && versionBorrar.getRfc().equals("") && versionBorrar.getVersion() == 0) {
/* 41 */             versionTemporal = versionBorrar;
/*    */             break;
/*    */           } 
/*    */         } 
/* 45 */         if (versionTemporal != null) {
/* 46 */           autorizaciones.remove(versionTemporal);
/*    */         }
/*    */       } 
/* 49 */     } catch (Exception e) {
/* 50 */       System.out.println("Validacion/Autorizacion contexto");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 56 */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doPost(req, resp); }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\ActualizaValidacionesServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */