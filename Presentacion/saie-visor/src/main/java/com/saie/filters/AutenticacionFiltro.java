package com.saie.filters;
/*     */ 
/*     */ import com.iecisa.sat.saie.response.ServiceResponse;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import com.saie.listeners.SessionListener;
/*     */ import java.io.IOException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.Filter;
/*     */ import javax.servlet.FilterChain;
/*     */ import javax.servlet.FilterConfig;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutenticacionFiltro implements Filter
/*     */ {
/*     */   public void destroy() {}
/*     */   
/*     */   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
/*  31 */     HttpServletRequest request = (HttpServletRequest)req;
/*  32 */     HttpServletResponse response = (HttpServletResponse)resp;
/*     */     
/*  34 */     String rfc = request.getHeader("saie_rfc_completo");
/*     */     
/*  36 */     UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuario");
/*  37 */     if ((usuario == null || !usuario.getNombre().equals(rfc)) && rfc != null && request.getServletPath().equals("/SAIEVisor")) {
/*  38 */       String agent = request.getHeader("User-Agent");
/*  39 */       System.out.println(agent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  53 */       String ip = request.getHeader("saie_ip");
/*  54 */       Pattern pattern = Pattern.compile("((\\d{1,3})\\.){3}(\\d{1,3})");
/*  55 */       Matcher matcher = pattern.matcher(ip);
/*  56 */       ip = "";
/*  57 */       while (matcher.find()) {
/*  58 */         if (!matcher.group().contains("127.0.0")) {
/*  59 */           ip = ip + ((ip != "") ? "| " : "") + matcher.group();
/*     */         }
/*     */       } 
/*  62 */       String mac = request.getHeader("saie_mac");
/*  63 */       pattern = Pattern.compile("([0-9A-F]{2}[:-]){5}([0-9A-F]{2})");
/*  64 */       matcher = pattern.matcher(mac);
/*  65 */       mac = "";
/*  66 */       while (matcher.find()) {
/*  67 */         mac = mac + ((mac != "") ? "| " : "") + matcher.group();
/*     */       }
/*     */       
/*  70 */       usuario = new UsuarioDTO(rfc, ip, mac, "", rfc);
/*  71 */       usuario.setLocalidad(request.getHeader("saie_alsc"));
/*  72 */       usuario.setNumeroSerieCertificado((request.getHeader("saie_serial") != null) ? request.getHeader("saie_serial") : "00000099999900000000");
/*     */       
/*  74 */       if (!SessionListener.existeSesion(rfc, request.getSession().getServletContext())) {
/*  75 */         request.getSession().invalidate();
/*  76 */         request.getSession().removeAttribute("usuario");
/*     */         
/*  78 */         ServiceResponse<UsuarioDTO> result = (new ServiceEnrolamiento()).autorizaUsuario(usuario);
/*  79 */         usuario = (UsuarioDTO)result.getResult();
/*     */         
/*  81 */         if (usuario != null && usuario.getPermisos().size() > 0) {
/*  82 */           request.getSession().setAttribute("usuario", usuario);
/*  83 */           if (usuario.getTabla_rfc() != null)
/*  84 */             request.getSession().setAttribute("tabla_rfc", usuario.getTabla_rfc()); 
/*  85 */           if (usuario.getTabla_casos() != null)
/*  86 */             request.getSession().setAttribute("tabla_casos", usuario.getTabla_casos()); 
/*  87 */           if (usuario.getTabla_hitlist() != null)
/*  88 */             request.getSession().setAttribute("tabla_hitlist", usuario.getTabla_hitlist()); 
/*  89 */           if (usuario.getTabla_validar() != null)
/*  90 */             request.getSession().setAttribute("tabla_validar", usuario.getTabla_validar()); 
/*     */         } 
/*     */       } else {
/*  93 */         request.getSession().setAttribute("usuarioPendiente", usuario);
/*  94 */         request.setAttribute("mensaje", "Existe otra sesión activa con este usuario");
/*  95 */         request.setAttribute("login", "pendiente");
/*  96 */         fc.doFilter(request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 101 */     if ((usuario != null && usuario.getPermisos().size() > 0) || request.getServletPath().equals("/login_error.jsp")) {
/* 102 */       fc.doFilter(request, response);
/* 103 */     } else if (usuario != null && usuario.getPermisos().size() == 0) {
/* 104 */       response.sendRedirect(request.getContextPath() + "/login_error.jsp?error=2");
/*     */     } else {
/* 106 */       response.sendRedirect(request.getRequestURL().substring(0, request.getRequestURL().indexOf(":")) + "://serviciosinternos.sat.gob.mx/AGLogout");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void init(FilterConfig arg0) throws ServletException {}
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\filters\AutenticacionFiltro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */