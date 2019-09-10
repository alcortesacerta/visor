/*    */ package com.saie.listeners;
/*    */ 
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO;
/*    */ import com.saie.listeners.SessionListener;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import javax.servlet.http.HttpSessionAttributeListener;
/*    */ import javax.servlet.http.HttpSessionBindingEvent;
/*    */ import javax.servlet.http.HttpSessionEvent;
/*    */ import javax.servlet.http.HttpSessionListener;
/*    */ 
/*    */ 
/*    */ public class SessionListener
/*    */   implements HttpSessionListener, HttpSessionAttributeListener
/*    */ {
/*    */   public void sessionDestroyed(HttpSessionEvent se) {
/* 19 */     UsuarioDTO usuario = (UsuarioDTO)se.getSession().getAttribute("usuario");
/* 20 */     if (usuario != null) {
/* 21 */       eliminarSesion(usuario.getRfc(), se.getSession().getServletContext());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void sessionCreated(HttpSessionEvent se) {}
/*    */ 
/*    */   
/*    */   public void attributeAdded(HttpSessionBindingEvent se) {
/* 30 */     if (se.getName().equals("usuario")) {
/* 31 */       List<HttpSession> sesionesActivas = getContextSessions(se.getSession().getServletContext());
/* 32 */       sesionesActivas.add(se.getSession());
/* 33 */       se.getSession().getServletContext().setAttribute("sesionesActivas", sesionesActivas);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void attributeRemoved(HttpSessionBindingEvent se) {}
/*    */ 
/*    */   
/*    */   public void attributeReplaced(HttpSessionBindingEvent se) {}
/*    */ 
/*    */   
/*    */   public static List<HttpSession> getContextSessions(ServletContext context) {
/* 45 */     List<HttpSession> sesionesActivas = (List)context.getAttribute("sesionesActivas");
/* 46 */     if (sesionesActivas == null) {
/* 47 */       sesionesActivas = new ArrayList<HttpSession>();
/*    */     }
/* 49 */     return sesionesActivas;
/*    */   }
/*    */   
/*    */   public static boolean existeSesion(String rfc, ServletContext context) {
/* 53 */     List<HttpSession> sesionesActivas = getContextSessions(context);
/* 54 */     for (HttpSession sesion : sesionesActivas) {
/* 55 */       UsuarioDTO usuario = (UsuarioDTO)sesion.getAttribute("usuario");
/* 56 */       if (usuario.getRfc().equals(rfc)) {
/* 57 */         return true;
/*    */       }
/*    */     } 
/* 60 */     return false;
/*    */   }
/*    */   
/*    */   public static void eliminarSesion(String rfc, ServletContext context) {
/* 64 */     List<HttpSession> sesionesActivas = getContextSessions(context);
/* 65 */     List<HttpSession> sesionesRemover = new ArrayList<HttpSession>();
/* 66 */     for (HttpSession sesion : sesionesActivas) {
/* 67 */       UsuarioDTO usuario = (UsuarioDTO)sesion.getAttribute("usuario");
/* 68 */       if (usuario.getRfc().equals(rfc)) {
/* 69 */         sesionesRemover.add(sesion);
/*    */       }
/*    */     } 
/* 72 */     for (HttpSession sesion : sesionesRemover) {
/* 73 */       sesionesActivas.remove(sesion);
/* 74 */       sesion.removeAttribute("usuario");
/*    */     } 
/* 76 */     context.setAttribute("sesionesActivas", sesionesActivas);
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\listeners\SessionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */