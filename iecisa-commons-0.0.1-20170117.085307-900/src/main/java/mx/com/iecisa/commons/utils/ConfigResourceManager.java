/*    */ package mx.com.iecisa.commons.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.net.URLClassLoader;
/*    */ import java.util.Locale;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class ConfigResourceManager
/*    */ {
/* 12 */   private ResourceBundle pHandler = null;
/*    */ 
/*    */   
/*    */   public ConfigResourceManager(String configPath, String locale, String resource) {
/* 16 */     if (configPath != null && !configPath.trim().isEmpty()) {
/* 17 */       File file = new File(configPath);
/* 18 */       URL url = null;
/*    */       try {
/* 20 */         url = file.toURI().toURL();
/* 21 */         URL[] urls = new URL[1];
/* 22 */         urls[0] = url;
/* 23 */         ClassLoader loader = new URLClassLoader(urls);
/*    */         
/* 25 */         this.pHandler = ResourceBundle.getBundle(resource, new Locale(locale), loader);
/* 26 */       } catch (MalformedURLException e) {
/* 27 */         e.printStackTrace();
/*    */       } 
/*    */     } else {
/* 30 */       System.out.println("CONFIG FILE NOT FOUND");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getString(String propertyId) {
/* 36 */     String result = null;
/*    */     
/*    */     try {
/* 39 */       result = this.pHandler.getString(propertyId);
/* 40 */     } catch (Exception e) {
/* 41 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 44 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\mx\com\iecisa\common\\utils\ConfigResourceManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */