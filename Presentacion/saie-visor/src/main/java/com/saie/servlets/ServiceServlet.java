/*     */ package com.saie.servlets;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.iecisa.sat.saie.audit.AuditManager;
/*     */ import com.iecisa.sat.saie.audit.dto.AuditEvent;
/*     */ import com.iecisa.sat.saie.response.ServiceResponse;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ArchivoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.BuscarEnrolamientoPorCriteriosDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.CatalogoDuplicidad;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ControlVersionesDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosDuplicidadDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.Permisos;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoAdjuntosDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceDuplicidad;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import com.saie.listeners.SessionListener;
/*     */ import com.saie.servlets.ServiceServlet;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServiceServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  48 */   private ServiceEnrolamiento servicio = new ServiceEnrolamiento();
/*  49 */   private Gson jsonencoder = null;
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*  53 */     String opcion = (req.getPathInfo() != null) ? req.getPathInfo() : req.getServletPath();
/*  54 */     if (opcion.equals("/busqueda_rfc")) {
/*  55 */       buscarRFC(req, resp);
/*  56 */     } else if (opcion.equals("/guarda_tabla_rfc")) {
/*  57 */       req.getSession().setAttribute("tabla_rfc", req.getParameter("orden").split(","));
/*  58 */       guardaEstadoTabla(req, resp);
/*  59 */     } else if (opcion.equals("/guarda_tabla_casos")) {
/*  60 */       req.getSession().setAttribute("tabla_casos", req.getParameter("orden").split(","));
/*  61 */       guardaEstadoTabla(req, resp);
/*  62 */     } else if (opcion.equals("/guarda_tabla_duplicidad")) {
/*  63 */       req.getSession().setAttribute("tabla_hitlist", req.getParameter("orden").split(","));
/*  64 */       guardaEstadoTabla(req, resp);
/*  65 */     } else if (opcion.equals("/guarda_tabla_validar")) {
/*  66 */       req.getSession().setAttribute("tabla_validar", req.getParameter("orden").split(","));
/*  67 */       guardaEstadoTabla(req, resp);
/*  68 */     } else if (opcion.equals("/lista_casos")) {
/*  69 */       req.setAttribute("catalogo", CatalogoDuplicidad.PD);
/*  70 */       listarCasos(req, resp);
/*  71 */     } else if (opcion.equals("/lista_duplicados")) {
/*  72 */       req.setAttribute("catalogo", CatalogoDuplicidad.CD);
/*  73 */       listarCasos(req, resp);
/*  74 */     } else if (opcion.equals("/lista_validar")) {
/*  75 */       listarValidaciones(req, resp);
/*  76 */     } else if (opcion.equals("/login")) {
/*  77 */       login(req, resp);
/*  78 */     } else if (opcion.equals("/logout")) {
/*  79 */       logout(req, resp);
/*  80 */     } else if (opcion.equals("/certificar_documentos")) {
/*  81 */       certificarDocumentos(req, resp);
/*  82 */     } else if (opcion.equals("/certificar_dictamen")) {
/*  83 */       certificarDictamen(req, resp);
/*  84 */     } else if (opcion.equals("/descargar_adjuntos")) {
/*  85 */       descargarAdjuntos(req, resp);
/*  86 */     } else if (opcion.equals("/limpiar_sesion")) {
/*  87 */       limpiaSesion(req, resp);
/*  88 */     } else if (opcion.equals("/loginSesion")) {
/*  89 */       loginPendiente(req, resp);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doPost(req, resp); }
/*     */ 
/*     */   
/*     */   private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 100 */     SessionListener.eliminarSesion(((UsuarioDTO)req.getSession().getAttribute("usuario")).getRfc(), getServletContext());
/* 101 */     req.getSession().invalidate();
/* 102 */     resp.sendRedirect(req.getRequestURL().substring(0, req.getRequestURL().indexOf(":")) + "://serviciosinternos.sat.gob.mx/AGLogout");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void buscarRFC(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 108 */     List<DatosEnrolamientoDTO> enrolamientoDtos = null;
/* 109 */     if ((req.getParameter("rfc") != null && req.getParameter("rfc").equals(req.getSession().getAttribute("buscarfc"))) || (req
/* 110 */       .getParameter("curp") != null && req.getParameter("curp").equals(req.getSession().getAttribute("buscacurp"))) || (req
/* 111 */       .getParameter("nombre") != null && req.getParameter("nombre").equals(req.getSession().getAttribute("buscanombre")))) {
/* 112 */       enrolamientoDtos = (List)req.getSession().getAttribute("enrolamientoDtoList");
/*     */     } else {
/* 114 */       req.getSession().removeAttribute("buscarfc");
/* 115 */       req.getSession().removeAttribute("buscacurp");
/* 116 */       req.getSession().removeAttribute("buscanombre");
/* 117 */       BuscarEnrolamientoPorCriteriosDTO busquedadto = new BuscarEnrolamientoPorCriteriosDTO();
/* 118 */       String amessage = null;
/* 119 */       if (req.getParameter("buscar").equals("rfc")) {
/* 120 */         amessage = "VisorBusqueda, RFC, " + req.getParameter("rfc");
/* 121 */         req.getSession().setAttribute("buscarfc", req.getParameter("rfc"));
/* 122 */         busquedadto.setRfc(req.getParameter("rfc"));
/* 123 */       } else if (req.getParameter("buscar").equals("curp")) {
/* 124 */         amessage = "VisorBusqueda, CURP, " + req.getParameter("curp");
/* 125 */         req.getSession().setAttribute("buscacurp", req.getParameter("curp"));
/* 126 */         busquedadto.setCurp(req.getParameter("curp"));
/* 127 */       } else if (req.getParameter("buscar").equals("nombre")) {
/* 128 */         amessage = "VisorBusqueda, Nombre, " + req.getParameter("nombre");
/* 129 */         req.getSession().setAttribute("buscanombre", req.getParameter("nombre"));
/* 130 */         busquedadto.setNombre(req.getParameter("nombre"));
/*     */       } 
/* 132 */       UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/* 133 */       ServiceResponse<List<DatosEnrolamientoDTO>> serviceResponse = this.servicio.getEnrolamientoPorCriterios(busquedadto, usuario.getPermisos().contains(Permisos.EXPUESTOS));
/*     */       
/* 135 */       if (serviceResponse.getCode() == 0) {
/* 136 */         AuditEvent aevent = new AuditEvent();
/* 137 */         aevent.setDateTime(new Date());
/* 138 */         aevent.setUser(usuario.getRfc());
/* 139 */         aevent.setIpAddress(usuario.getIp());
/* 140 */         aevent.setMacAddress(usuario.getMac());
/* 141 */         aevent.setMessage(amessage);
/* 142 */         aevent.setUuid(UUID.randomUUID().toString());
/* 143 */         AuditManager.register(aevent);
/*     */         
/* 145 */         enrolamientoDtos = (List)serviceResponse.getResult();
/* 146 */         if (enrolamientoDtos.size() > 1) {
/* 147 */           List<DatosEnrolamientoDTO> repetidos = new ArrayList<DatosEnrolamientoDTO>();
/* 148 */           for (int i = 0; i < enrolamientoDtos.size(); i++) {
/* 149 */             DatosEnrolamientoDTO datosAuxiliar = (DatosEnrolamientoDTO)enrolamientoDtos.get(i);
/* 150 */             for (int j = 0; j < i; j++) {
/* 151 */               if (((DatosEnrolamientoDTO)enrolamientoDtos.get(j)).getRfc().equals(datosAuxiliar.getRfc())) {
/* 152 */                 repetidos.add(datosAuxiliar);
/*     */               }
/*     */             } 
/*     */           } 
/* 156 */           for (int i = 0; i < repetidos.size(); i++) {
/* 157 */             enrolamientoDtos.remove(repetidos.get(i));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 164 */     String jsonresponse = "";
/* 165 */     this.jsonencoder = (new GsonBuilder()).setDateFormat("dd/MM/yy").create();
/* 166 */     if (enrolamientoDtos != null) {
/* 167 */       jsonresponse = "{";
/* 168 */       for (int i = 0; i < enrolamientoDtos.size(); i++) {
/* 169 */         if (i != 0) {
/* 170 */           jsonresponse = jsonresponse + ",";
/*     */         }
/* 172 */         jsonresponse = jsonresponse + "\"rfc" + i + "\":";
/* 173 */         DatosEnrolamientoDTO contribuyente = (DatosEnrolamientoDTO)enrolamientoDtos.get(i);
/* 174 */         String row = this.jsonencoder.toJson(contribuyente);
/* 175 */         jsonresponse = jsonresponse + row;
/*     */       } 
/* 177 */       jsonresponse = jsonresponse + "}";
/*     */     } else {
/* 179 */       System.out.println("error en el servicio");
/* 180 */       jsonresponse = "{\"mensaje\":\"error\"}";
/*     */     } 
/* 182 */     req.getSession().setAttribute("enrolamientoDtoList", enrolamientoDtos);
/* 183 */     resp.setCharacterEncoding("utf-8");
/* 184 */     resp.setContentType("application/json");
/* 185 */     resp.getWriter().println(jsonresponse);
/*     */   }
/*     */ 
/*     */   
/*     */   private void listarValidaciones(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 190 */     String jsonresponse = "";
/*     */     try {
/* 192 */       UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/*     */       
/* 194 */       ServiceResponse<List<ControlVersionesDTO>> response = this.servicio.getActualizacionesPendientes();
/* 195 */       System.out.println("LocalidadValidaciones actualizacionesPendientes " + response);
/* 196 */       List<ControlVersionesDTO> validaciones = (List)getServletContext().getAttribute("validacionesDTO");
/* 197 */       List<ControlVersionesDTO> autorizaciones = (List)getServletContext().getAttribute("autorizacionesDTO");
/*     */       
/* 199 */       this.jsonencoder = (new GsonBuilder()).setDateFormat("dd/MM/yy").create();
/*     */       
/* 201 */       if (response.getCode() == 0 && response.getResult() != null) {
/* 202 */         List<ControlVersionesDTO> controldtos = (List)response.getResult();
/* 203 */         synchronized (validaciones) {
/* 204 */           for (ControlVersionesDTO control : controldtos) {
/* 205 */             if (control.getEstatus().intValue() == 3) {
/* 206 */               validaciones.add(control); continue;
/* 207 */             }  if (control.getEstatus().intValue() == 5) {
/* 208 */               autorizaciones.add(control);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 214 */       List<ControlVersionesDTO> controldtos = new ArrayList<ControlVersionesDTO>();
/* 215 */       ServiceResponse<List<String>> responseUnidadesLocalidad = this.servicio.getUnidadesLocalidad(usuario.getLocalidad());
/* 216 */       System.out.println("LocalidadValidaciones unidadesLocalidad " + responseUnidadesLocalidad);
/* 217 */       List<String> unidadesLocalidad = (List)responseUnidadesLocalidad.getResult();
/* 218 */       if (unidadesLocalidad != null && unidadesLocalidad.size() > 0) {
/* 219 */         synchronized (validaciones) {
/* 220 */           if (usuario.getPermisos().contains(Permisos.VALIDAR) && !usuario.getPermisos().contains(Permisos.VALIDA_NACIONAL)) {
/* 221 */             for (ControlVersionesDTO validacion : validaciones) {
/* 222 */               if (validacion != null && 
/* 223 */                 unidadesLocalidad.contains(validacion.getLocalidadEnrolamiento())) {
/* 224 */                 controldtos.add(validacion);
/*     */               }
/*     */             }
/*     */           
/* 228 */           } else if (usuario.getPermisos().contains(Permisos.VALIDAR)) {
/* 229 */             controldtos.addAll(validaciones);
/*     */           } 
/*     */           
/* 232 */           if (usuario.getPermisos().contains(Permisos.AUTORIZAR) && !usuario.getPermisos().contains(Permisos.VALIDA_NACIONAL)) {
/* 233 */             for (ControlVersionesDTO autorizacion : autorizaciones) {
/* 234 */               if (autorizacion != null && 
/* 235 */                 unidadesLocalidad.contains(autorizacion.getLocalidadEnrolamiento())) {
/* 236 */                 controldtos.add(autorizacion);
/*     */               }
/*     */             }
/*     */           
/* 240 */           } else if (usuario.getPermisos().contains(Permisos.AUTORIZAR)) {
/* 241 */             controldtos.addAll(autorizaciones);
/*     */           } 
/*     */         } 
/* 244 */         System.out.println("LocalidadValidaciones size:" + controldtos.size());
/*     */       }
/* 246 */       else if (unidadesLocalidad == null) {
/* 247 */         System.out.println("LocalidadValidaciones unidadesLocalidad:null");
/*     */       } else {
/* 249 */         System.out.println("LocalidadValidaciones unidadesLocalidad:0");
/*     */       } 
/*     */       
/* 252 */       jsonresponse = this.jsonencoder.toJson(controldtos);
/* 253 */       req.getSession().setAttribute("controlDtoList", controldtos);
/* 254 */       resp.setCharacterEncoding("utf-8");
/* 255 */       resp.setContentType("application/json");
/* 256 */       resp.getWriter().println(jsonresponse);
/* 257 */     } catch (Exception e) {
/* 258 */       System.out.println("Exception LocalidadValidaciones");
/* 259 */       e.printStackTrace();
/*     */       
/* 261 */       jsonresponse = "{\"mensaje\":\"error\"}";
/* 262 */       resp.setCharacterEncoding("utf-8");
/* 263 */       resp.setContentType("application/json");
/* 264 */       resp.getWriter().println(jsonresponse);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void listarCasos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 269 */     ServiceDuplicidad sd = new ServiceDuplicidad();
/* 270 */     ServiceResponse<List<DatosDuplicidadDTO>> response = sd.getListadoDuplicidadPorCriterios();
/*     */ 
/*     */     
/* 273 */     List<DatosDuplicidadDTO> duplicadosdtos = (List)getServletContext().getAttribute("duplicadosDTO");
/* 274 */     String jsonresponse = "";
/* 275 */     this.jsonencoder = (new GsonBuilder()).setDateFormat("dd/MM/yy").create();
/*     */     
/* 277 */     if (response.getCode() == 0) {
/* 278 */       duplicadosdtos.addAll((Collection)response.getResult());
/* 279 */       getServletContext().setAttribute("duplicadosDTO", duplicadosdtos);
/*     */     } else {
/* 281 */       System.out.println("error en el servicio");
/*     */     } 
/* 283 */     if (duplicadosdtos.size() > 0) {
/* 284 */       jsonresponse = this.jsonencoder.toJson(duplicadosdtos);
/*     */     } else {
/* 286 */       jsonresponse = "{\"mensaje\":\"error\"}";
/*     */     } 
/* 288 */     resp.setCharacterEncoding("utf-8");
/* 289 */     resp.setContentType("application/json");
/* 290 */     resp.getWriter().println(jsonresponse);
/*     */   }
/*     */   
/*     */   private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 294 */     String rfccer = "";
/*     */     
/* 296 */     if (ServletFileUpload.isMultipartContent(req)) {
/* 297 */       DiskFileItemFactory factory = new DiskFileItemFactory();
/* 298 */       factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
/* 299 */       ServletFileUpload upload = new ServletFileUpload(factory);
/*     */       
/* 301 */       InputStream certificado = null;
/* 302 */       InputStream llave = null;
/* 303 */       String pass = "";
/*     */       try {
/* 305 */         List<FileItem> formItems = upload.parseRequest(req);
/* 306 */         if (formItems != null && formItems.size() > 0) {
/* 307 */           for (FileItem item : formItems) {
/* 308 */             if (!item.isFormField()) {
/* 309 */               if (item.getName().contains(".cer")) {
/* 310 */                 certificado = item.getInputStream(); continue;
/* 311 */               }  if (item.getName().contains(".key"))
/* 312 */                 llave = item.getInputStream(); 
/*     */               continue;
/*     */             } 
/* 315 */             if (item.getFieldName().equals("claveFiel")) {
/* 316 */               pass = item.getString(); continue;
/* 317 */             }  if (item.getFieldName().equals("usuarioFiel")) {
/* 318 */               rfccer = item.getString();
/*     */             }
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 324 */         String textoPorFirmar = "ValidacionDeFirma";
/* 325 */         if (certificado != null && llave != null) {
/* 326 */           ServiceResponse<String> responseFiel = this.servicio.validarFirmarFiel(certificado, llave, pass, textoPorFirmar, null);
/* 327 */           if (responseFiel.getCode() == -1) {
/* 328 */             req.setAttribute("mensaje", responseFiel.getMessage());
/*     */           } else {
/* 330 */             rfccer = responseFiel.getMessage();
/*     */           }
/*     */         
/*     */         } 
/* 334 */       } catch (Exception e) {
/* 335 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 339 */     UsuarioDTO usuario = null;
/*     */     
/* 341 */     if (req.getAttribute("mensaje") == null)
/*     */     {
/* 343 */       if (!SessionListener.existeSesion(rfccer, getServletContext())) {
/*     */         
/* 345 */         usuario = new UsuarioDTO(rfccer, req.getRemoteAddr(), "00:00:00:00:00:00", "", rfccer);
/* 346 */         ServiceResponse<UsuarioDTO> result = this.servicio.autorizaUsuario(usuario);
/*     */         
/* 348 */         usuario = (UsuarioDTO)result.getResult();
/*     */         
/* 350 */         if (usuario != null && usuario.getPermisos().size() > 0) {
/* 351 */           req.getSession().setAttribute("usuario", usuario);
/* 352 */           req.setAttribute("mensaje", "ok");
/*     */           
/* 354 */           if (usuario.getTabla_rfc() != null)
/* 355 */             req.getSession().setAttribute("tabla_rfc", usuario.getTabla_rfc()); 
/* 356 */           if (usuario.getTabla_casos() != null)
/* 357 */             req.getSession().setAttribute("tabla_casos", usuario.getTabla_casos()); 
/* 358 */           if (usuario.getTabla_hitlist() != null)
/* 359 */             req.getSession().setAttribute("tabla_hitlist", usuario.getTabla_hitlist()); 
/* 360 */           if (usuario.getTabla_validar() != null)
/* 361 */             req.getSession().setAttribute("tabla_validar", usuario.getTabla_validar()); 
/*     */         } else {
/* 363 */           req.setAttribute("mensaje", "No cuenta con permisos para el aplicativo");
/*     */         } 
/*     */       } else {
/* 366 */         req.getSession().setAttribute("usuarioPendiente", rfccer);
/* 367 */         req.setAttribute("mensaje", "Existe otra sesión activa con este usuario");
/* 368 */         req.setAttribute("login", "pendiente");
/*     */       } 
/*     */     }
/*     */     
/* 372 */     if (req.getAttribute("mensaje").equals("ok")) {
/* 373 */       getServletContext().getRequestDispatcher("/menu.jsp").forward(req, resp);
/*     */     } else {
/* 375 */       getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void loginPendiente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 380 */     UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuarioPendiente");
/* 381 */     req.getSession().removeAttribute("usuarioPendiente");
/*     */     
/* 383 */     SessionListener.eliminarSesion(usuario.getRfc(), getServletContext());
/*     */     
/* 385 */     ServiceResponse<UsuarioDTO> result = this.servicio.autorizaUsuario(usuario);
/* 386 */     usuario = (UsuarioDTO)result.getResult();
/*     */     
/* 388 */     if (usuario != null && usuario.getPermisos().size() > 0) {
/* 389 */       req.getSession().setAttribute("usuario", usuario);
/* 390 */       req.setAttribute("mensaje", "ok");
/*     */       
/* 392 */       if (usuario.getTabla_rfc() != null)
/* 393 */         req.getSession().setAttribute("tabla_rfc", usuario.getTabla_rfc()); 
/* 394 */       if (usuario.getTabla_casos() != null)
/* 395 */         req.getSession().setAttribute("tabla_casos", usuario.getTabla_casos()); 
/* 396 */       if (usuario.getTabla_hitlist() != null)
/* 397 */         req.getSession().setAttribute("tabla_hitlist", usuario.getTabla_hitlist()); 
/* 398 */       if (usuario.getTabla_validar() != null)
/* 399 */         req.getSession().setAttribute("tabla_validar", usuario.getTabla_validar()); 
/*     */     } else {
/* 401 */       req.setAttribute("mensaje", "No cuenta con permisos para el aplicativo");
/*     */     } 
/*     */     
/* 404 */     if (req.getAttribute("mensaje").equals("ok")) {
/* 405 */       getServletContext().getRequestDispatcher("/menu.jsp").forward(req, resp);
/*     */     } else {
/* 407 */       getServletContext().getRequestDispatcher("/login_error.jsp").forward(req, resp);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void guardaEstadoTabla(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*     */     try {
/* 413 */       UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/* 414 */       String directorio = System.getProperty("USER_PROPERTIES");
/* 415 */       File f = new File(directorio + "/" + usuario.getNombre() + "_orden_tabla.ser");
/* 416 */       FileOutputStream file = new FileOutputStream(f);
/* 417 */       ObjectOutputStream outStream = new ObjectOutputStream(file);
/* 418 */       outStream.writeObject(req.getSession().getAttribute("tabla_rfc"));
/* 419 */       outStream.writeObject(req.getSession().getAttribute("tabla_casos"));
/* 420 */       outStream.writeObject(req.getSession().getAttribute("tabla_hitlist"));
/* 421 */       outStream.writeObject(req.getSession().getAttribute("tabla_validar"));
/* 422 */       outStream.flush();
/* 423 */       outStream.close();
/* 424 */       file.close();
/* 425 */     } catch (Exception e) {
/* 426 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void certificarDocumentos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 431 */     DatosEnrolamientoDTO datosdto = (DatosEnrolamientoDTO)req.getSession().getAttribute("enrolamientoDto");
/* 432 */     VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO)req.getSession().getAttribute("versionEnrolamiento");
/* 433 */     VersionEnrolamientoAdjuntosDTO adjuntosdto = (VersionEnrolamientoAdjuntosDTO)req.getSession().getAttribute("adjuntosDto");
/* 434 */     String ids = req.getParameter("documentos");
/* 435 */     UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/*     */     
/* 437 */     ServiceResponse<ArchivoDTO> response = servicio.getCertificacionDocumentos(datosdto, versiondto, adjuntosdto, ids.split(","),
				req.getParameter("nombrealsc"),req.getParameter("fraccionalsc"),req.getParameter("admin"),req.getParameter("ciudad"),usuario,
				req.getParameter("tipoLeyenda"));
/*     */     
/* 448 */     resp.setContentType("application/json");
/* 449 */     if (response.getCode() == 0) {
/* 450 */       req.getSession().setAttribute("certificacionPDF", response.getResult());
/* 451 */       String jsonresponse = "{\"mensaje\":\"ok\"}";
/* 452 */       resp.getWriter().println(jsonresponse);
/*     */     } else {
/* 454 */       String jsonresponse = "{\"mensaje\":\"error\"}";
/* 455 */       resp.getWriter().println(jsonresponse);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void certificarDictamen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 460 */     UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/*     */     
/* 462 */     ServiceResponse<ArchivoDTO> response = null;
/* 463 */     if (req.getParameter("tipo").equals("central")) {
/* 464 */       response = this.servicio.getCertificacionDictamenCentral(req.getParameter("dictamenUrl"), req
/* 465 */           .getParameter("numeroastc"), req.getParameter("nombrealsc"), req.getParameter("admin"), req.getParameter("ciudad"), 
/* 466 */           req.getParameter("ausenciaCheck").equals("true") ? req.getParameter("fsubadmin") : null, req.getParameter("numerosubadmin"), req
/* 467 */           .getParameter("nombre"), req.getParameter("nombre2"), req.getParameter("rfc"), req.getParameter("rfc2"), req
/* 468 */           .getParameter("localidad"), req.getParameter("localidad2"), req.getParameter("fecha"), req.getParameter("fecha2"), usuario);
/*     */     } else {
/* 470 */       response = this.servicio.getCertificacionDictamenDesconcentrado(req.getParameter("dictamenUrl"), req
/* 471 */           .getParameter("fraccion"), req.getParameter("inciso"), req.getParameter("nombrealsc"), req.getParameter("admin"), req.getParameter("ciudad"), 
/* 472 */           req.getParameter("ausenciaCheck").equals("true") ? req.getParameter("fsubadmin") : null, req
/* 473 */           .getParameter("nombre"), req.getParameter("nombre2"), req.getParameter("rfc"), req.getParameter("rfc2"), req
/* 474 */           .getParameter("localidad"), req.getParameter("localidad2"), req.getParameter("fecha"), req.getParameter("fecha2"), usuario);
/*     */     } 
/*     */     
/* 477 */     resp.setContentType("application/json");
/* 478 */     if (response.getCode() == 0) {
/* 479 */       req.getSession().setAttribute("certificacionPDF", response.getResult());
/* 480 */       String jsonresponse = "{\"mensaje\":\"ok\"}";
/* 481 */       resp.getWriter().println(jsonresponse);
/*     */     } else {
/* 483 */       String jsonresponse = "{\"mensaje\":\"error\"}";
/* 484 */       resp.getWriter().println(jsonresponse);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void descargarAdjuntos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 489 */     DatosEnrolamientoDTO datosdto = (DatosEnrolamientoDTO)req.getSession().getAttribute("enrolamientoDto");
/* 490 */     VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO)req.getSession().getAttribute("versionEnrolamiento");
/* 491 */     VersionEnrolamientoAdjuntosDTO adjuntosdto = (VersionEnrolamientoAdjuntosDTO)req.getSession().getAttribute("adjuntosDto");
/* 492 */     String ids = req.getParameter("adjuntos");
/* 493 */     UsuarioDTO usuario = (UsuarioDTO)req.getSession().getAttribute("usuario");
/*     */     
/* 495 */     ServiceResponse<ArchivoDTO> response = this.servicio.getZipAdjuntos(datosdto, versiondto, adjuntosdto, ids.split(","), req.getParameter("tipo"), usuario);
/*     */ 
/*     */     
/* 498 */     resp.setContentType("application/json");
/* 499 */     if (response.getCode() == 0) {
/* 500 */       req.getSession().setAttribute("adjuntosZIP", response.getResult());
/* 501 */       String jsonresponse = "{\"mensaje\":\"ok\"}";
/* 502 */       resp.getWriter().println(jsonresponse);
/*     */     } else {
/* 504 */       String jsonresponse = "{\"mensaje\":\"error\"}";
/* 505 */       resp.getWriter().println(jsonresponse);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void limpiaSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/* 511 */     String tipo = req.getParameter("tipo");
/* 512 */     HttpSession sesion = req.getSession();
/* 513 */     Enumeration<String> atributos = req.getSession().getAttributeNames();
/* 514 */     if (tipo != null && tipo.equals("buscar")) {
/* 515 */       while (atributos.hasMoreElements()) {
/* 516 */         String atributo = (String)atributos.nextElement();
/* 517 */         if (atributo.indexOf("tabla") == 0 || atributo.indexOf("javamelody") == 0 || atributo.equals("usuario") || atributo
/* 518 */           .indexOf("busca") == 0 || atributo.equals("enrolamientoDtoList") || atributo.equals("controlDtoList")) {
/*     */           continue;
/*     */         }
/* 521 */         sesion.removeAttribute(atributo);
/*     */       } 
/*     */     } else {
/*     */       
/* 525 */       while (atributos.hasMoreElements()) {
/* 526 */         String atributo = (String)atributos.nextElement();
/* 527 */         if (atributo.indexOf("tabla") == 0 || atributo.indexOf("javamelody") == 0 || atributo.equals("usuario")) {
/*     */           continue;
/*     */         }
/* 530 */         sesion.removeAttribute(atributo);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\servlets\ServiceServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */