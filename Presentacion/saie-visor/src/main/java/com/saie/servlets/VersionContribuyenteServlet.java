/*     */ package com.saie.servlets;
/*     */ 
/*     */ import com.iecisa.sat.saie.audit.AuditManager;
/*     */ import com.iecisa.sat.saie.audit.dto.AuditEvent;
/*     */ import com.iecisa.sat.saie.response.ServiceResponse;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.BuscarEnrolamientoPorCriteriosDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ControlVersionesDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.Permisos;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import com.saie.servlets.VersionContribuyenteServlet;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ public class VersionContribuyenteServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  28 */   private ServiceEnrolamiento servicio = new ServiceEnrolamiento();
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*  33 */     String rfc = req.getParameter("rfc");
/*     */     
/*  35 */     if (rfc != null && rfc != "") {
/*  36 */       rfc = rfc.replaceAll("_", "Ñ");
/*  37 */       req.getSession().setAttribute("rfc", rfc);
/*     */     } else {
/*  39 */       rfc = (String)req.getSession().getAttribute("rfc");
/*     */     } 
/*  41 */     String version = req.getParameter("version");
/*  42 */     if (version != null && version != "") {
/*  43 */       req.getSession().setAttribute("version", version);
/*     */     } else {
/*  45 */       version = (String)req.getSession().getAttribute("version");
/*     */     } 
/*  47 */     int ver = 0;
/*     */     try {
/*  49 */       ver = Integer.parseInt(version);
/*  50 */     } catch (Exception e) {
/*  51 */       ver = 0;
/*     */     } 
/*  53 */     DatosEnrolamientoDTO enrolamientoDto = (DatosEnrolamientoDTO)req.getSession().getAttribute("enrolamientoDto");
/*  54 */     if (enrolamientoDto != null && enrolamientoDto.getVersiones().size() > 0 && enrolamientoDto.getRfc().equals(rfc)) {
/*  55 */       if (!((VersionEnrolamientoGeneralDTO)enrolamientoDto.getVersiones().get(ver)).equals(req.getSession().getAttribute("versionEnrolamiento"))) {
/*  56 */         req.getSession().setAttribute("versionEnrolamiento", enrolamientoDto.getVersiones().get(ver));
/*  57 */         this.servicio.getVersionFotoFirma((VersionEnrolamientoGeneralDTO)enrolamientoDto.getVersiones().get(ver), enrolamientoDto.getRfc());
/*  58 */         req.getSession().removeAttribute("adjuntosDto");
/*     */       } 
/*     */       return;
/*     */     } 
/*  62 */     enrolamientoDto = null;
/*     */ 
/*     */     
/*  65 */     List<DatosEnrolamientoDTO> enrolamientoDtos = (List)req.getSession().getAttribute("enrolamientoDtoList");
/*  66 */     List<ControlVersionesDTO> controlDtos = null;
/*  67 */     UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/*  68 */     boolean validando = false;
/*  69 */     req.getSession().setAttribute("referer", "buscar");
/*  70 */     if ((usuario != null && usuario.getPermisos().contains(Permisos.VALIDAR)) || usuario.getPermisos().contains(Permisos.AUTORIZAR)) {
/*  71 */       controlDtos = (List)req.getSession().getAttribute("controlDtoList");
/*  72 */       System.out.println("VersionContribuyente controlDTO " + ((controlDtos != null) ? 1 : 0));
/*  73 */       if (controlDtos != null) {
/*  74 */         validando = true;
/*  75 */         req.getSession().setAttribute("referer", "validar");
/*  76 */         for (int i = 0; i < controlDtos.size(); i++) {
/*  77 */           if (((ControlVersionesDTO)controlDtos.get(i)).getRfc().equals(rfc)) {
/*  78 */             BuscarEnrolamientoPorCriteriosDTO busquedadto = new BuscarEnrolamientoPorCriteriosDTO();
/*  79 */             busquedadto.setRfc(rfc);
/*  80 */             ServiceResponse<List<DatosEnrolamientoDTO>> serviceResponse = this.servicio.getEnrolamientoPorCriterios(busquedadto, true);
/*  81 */             enrolamientoDtos = (List)serviceResponse.getResult();
/*  82 */             if (enrolamientoDtos.size() == 0) {
/*  83 */               enrolamientoDto = new DatosEnrolamientoDTO();
/*  84 */               enrolamientoDto.setRfc(rfc);
/*  85 */               enrolamientoDtos.add(enrolamientoDto);
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  93 */     System.out.println("VersionContribuyente enrolamientoDTOs " + ((enrolamientoDtos != null) ? 1 : 0));
/*  94 */     if (enrolamientoDtos != null) {
/*  95 */       for (int i = 0; i < enrolamientoDtos.size(); i++) {
/*  96 */         if (((DatosEnrolamientoDTO)enrolamientoDtos.get(i)).getRfc().equals(rfc)) {
/*  97 */           enrolamientoDto = (DatosEnrolamientoDTO)enrolamientoDtos.get(i);
/*     */           break;
/*     */         } 
/*     */       } 
/* 101 */       System.out.println("VersionContribuyente enrolamientoDTO " + ((enrolamientoDto != null) ? 1 : 0));
/* 102 */       if (enrolamientoDto != null) {
/* 103 */         if (System.getProperty("SAIE_CONSULTAR_SISE").equals("true")) {
/* 104 */           boolean bsise = this.servicio.getEnrolamientoSise(enrolamientoDto.getRfc(), usuario.getRfc());
/* 105 */           AuditEvent aevent = new AuditEvent();
/* 106 */           aevent.setDateTime(new Date());
/* 107 */           aevent.setUser(usuario.getRfc());
/* 108 */           aevent.setIpAddress(usuario.getIp());
/* 109 */           aevent.setMacAddress(usuario.getMac());
/* 110 */           String amessage = "ConsultaSISE, " + enrolamientoDto.getRfc() + ", " + bsise;
/* 111 */           aevent.setMessage(amessage);
/* 112 */           aevent.setUuid(UUID.randomUUID().toString());
/* 113 */           AuditManager.register(aevent);
/* 114 */           if (!bsise) {
/* 115 */             System.out.println("sise error");
/* 116 */             req.setAttribute("sise", "error");
/*     */             return;
/*     */           } 
/*     */         } else {
/* 120 */           req.setAttribute("sise", "bien");
/*     */         } 
/* 122 */         ServiceResponse<List<VersionEnrolamientoGeneralDTO>> result = this.servicio.getVersionEnrolamientoByRFC(rfc);
/*     */         
/* 124 */         System.out.println("VersionContribuyente versionDTO " + result.getCode() + " " + ((result.getResult() != null) ? ((List)result.getResult()).size() : -1) + " " + rfc);
/* 125 */         if (result.getCode() == 0) {
/* 126 */           List<VersionEnrolamientoGeneralDTO> temp = (List)result.getResult();
/* 127 */           List<VersionEnrolamientoGeneralDTO> versiones = new ArrayList<VersionEnrolamientoGeneralDTO>();
/* 128 */           for (int i = 0; i < temp.size(); i++) {
/* 129 */             if (usuario.getPermisos().contains(Permisos.VALIDAR) && ((VersionEnrolamientoGeneralDTO)temp.get(i)).getEstatus() == 3 && validando) {
/* 130 */               versiones.add(temp.get(i));
/* 131 */             } else if (usuario.getPermisos().contains(Permisos.AUTORIZAR) && ((VersionEnrolamientoGeneralDTO)temp.get(i)).getEstatus() == 5 && validando) {
/* 132 */               versiones.add(temp.get(i));
/* 133 */             } else if (!validando && (((VersionEnrolamientoGeneralDTO)temp.get(i)).getEstatus() == 1 || ((VersionEnrolamientoGeneralDTO)temp.get(i)).getEstatus() == 2 || ((VersionEnrolamientoGeneralDTO)temp.get(i)).getEstatus() == 6)) {
/* 134 */               versiones.add(temp.get(i));
/*     */             } 
/*     */           } 
/* 137 */           System.out.println("VersionContribuyente versionDTO " + versiones.size());
/* 138 */           enrolamientoDto.setVersiones(versiones);
/* 139 */           req.getSession().setAttribute("enrolamientoDto", enrolamientoDto);
/*     */           
/* 141 */           if (versiones.size() <= 0 || ver < 0) {
/* 142 */             req.getSession().removeAttribute("enrolamientoDto");
/* 143 */             req.getSession().removeAttribute("versionEnrolamiento");
/* 144 */             req.getSession().removeAttribute("adjuntosDto");
/*     */           } else {
/* 146 */             VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO)versiones.get(ver);
/* 147 */             req.getSession().setAttribute("versionEnrolamiento", versiondto);
/* 148 */             this.servicio.getVersionFotoFirma(versiondto, enrolamientoDto.getRfc());
/* 149 */             req.getSession().removeAttribute("adjuntosDto");
/*     */           } 
/*     */         } else {
/* 152 */           req.getSession().removeAttribute("enrolamientoDto");
/* 153 */           req.getSession().removeAttribute("versionEnrolamiento");
/* 154 */           req.getSession().removeAttribute("adjuntosDto");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\VersionContribuyenteServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */