/*     */ package com.saie.listeners;
/*     */ 
/*     */ import com.github.jaiimageio.impl.plugins.raw.RawImageReaderSpi;
/*     */ import com.github.jaiimageio.impl.plugins.tiff.TIFFImageReaderSpi;
/*     */ import com.iecisa.sat.saie.dao.DBManagerSaie;
/*     */ import com.iecisa.sat.saie.response.ServiceResponse;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.CatalogoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ControlVersionesDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosDuplicidadDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceDuplicidad;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import com.saie.listeners.ContextListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.imageio.spi.IIORegistry;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ import javax.servlet.ServletContextEvent;
/*     */ import javax.servlet.ServletContextListener;
/*     */ 
/*     */ 
/*     */ public class ContextListener
/*     */   implements ServletContextListener
/*     */ {
/*     */   public void contextInitialized(ServletContextEvent sce) {
/*  27 */     ServiceResponse<List<CatalogoDTO>> response = (new ServiceEnrolamiento()).cargarCatalogoTipoObservacionDTO();
/*  28 */     if (response.getCode() == 0) {
/*  29 */       List<CatalogoDTO> catalogo = (List)response.getResult();
/*  30 */       sce.getServletContext().setAttribute("catalogoTipoObservacion", catalogo);
/*     */     } else {
/*  32 */       System.out.println("Error al cargar el catalogo tipo observacion, por favor reinicie el .war");
/*     */     } 
/*     */     
/*  35 */     ServiceResponse<List<CatalogoDTO>> resp = (new ServiceEnrolamiento()).cargarCatalogoTipoResolucionDTO();
/*  36 */     if (resp.getCode() == 0) {
/*  37 */       List<CatalogoDTO> catalogo = (List)resp.getResult();
/*  38 */       sce.getServletContext().setAttribute("catalogoTipoResolucion", catalogo);
/*     */     } else {
/*     */       
/*  41 */       System.out.println("Error al cargar el catalogo tipo resolucion, por favor reinicie el .war");
/*     */     } 
/*  43 */     ServiceResponse<List<DatosDuplicidadDTO>> responseDuplicados = (new ServiceDuplicidad()).getListadoDuplicidadPorCriterios();
/*  44 */     List<DatosDuplicidadDTO> duplicados = null;
/*  45 */     if (responseDuplicados.getCode() == 0) {
/*  46 */       duplicados = (List)responseDuplicados.getResult();
/*     */     } else {
/*  48 */       duplicados = new ArrayList<DatosDuplicidadDTO>();
/*     */     } 
/*  50 */     sce.getServletContext().setAttribute("duplicadosDTO", duplicados);
/*     */     
/*  52 */     ServiceResponse<List<ControlVersionesDTO>> responseAutorizaciones = (new ServiceEnrolamiento()).getActualizacionesPendientes();
/*  53 */     List<ControlVersionesDTO> autval = null;
/*  54 */     if (responseAutorizaciones.getCode() == 0) {
/*  55 */       autval = (List)responseAutorizaciones.getResult();
/*     */     } else {
/*  57 */       autval = new ArrayList<ControlVersionesDTO>();
/*     */     } 
/*  59 */     List<ControlVersionesDTO> autorizaciones = new ArrayList<ControlVersionesDTO>();
/*  60 */     List<ControlVersionesDTO> validaciones = new ArrayList<ControlVersionesDTO>();
/*  61 */     for (ControlVersionesDTO control : autval) {
/*  62 */       if (control.getEstatus().intValue() == 3) {
/*  63 */         validaciones.add(control); continue;
/*  64 */       }  if (control.getEstatus().intValue() == 5) {
/*  65 */         autorizaciones.add(control);
/*     */       }
/*     */     } 
/*  68 */     sce.getServletContext().setAttribute("validacionesDTO", validaciones);
/*  69 */     sce.getServletContext().setAttribute("autorizacionesDTO", autorizaciones);
/*     */     
/*     */     try {
/*  72 */       Properties properties = new Properties();
/*  73 */       properties.load(getClass().getResourceAsStream("/version.properties"));
/*  74 */       sce.getServletContext().setAttribute("version", properties.getProperty("fecha.version"));
/*  75 */     } catch (Exception e) {
/*  76 */       System.out.println("Error al cargar la version, por favor reinicie el .war");
/*     */     } 
/*     */ 
/*     */     
/*  80 */     IIORegistry registro = IIORegistry.getDefaultInstance();
/*  81 */     Iterator<ImageReaderSpi> itsp = registro.getServiceProviders(ImageReaderSpi.class, false);
/*     */     
/*  83 */     boolean breader = false;
/*  84 */     int i = 1;
/*  85 */     while (itsp.hasNext()) {
/*  86 */       ImageReaderSpi irs = (ImageReaderSpi)itsp.next();
/*  87 */       if (irs.getVendorName().contains("github.com")) {
/*  88 */         breader = true;
/*     */       }
/*     */     } 
/*  91 */     if (!breader) {
/*  92 */       registro.registerServiceProvider(new TIFFImageReaderSpi());
/*  93 */       registro.registerServiceProvider(new RawImageReaderSpi());
/*     */     } 
/*  95 */     itsp = registro.getServiceProviders(ImageReaderSpi.class, false);
/*  96 */     while (itsp.hasNext()) {
/*  97 */       ImageReaderSpi irs = (ImageReaderSpi)itsp.next();
/*  98 */       System.out.println(i + ": " + irs.getPluginClassName());
/*  99 */       i++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void contextDestroyed(ServletContextEvent sce) { DBManagerSaie.closePool(); }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\listeners\ContextListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */