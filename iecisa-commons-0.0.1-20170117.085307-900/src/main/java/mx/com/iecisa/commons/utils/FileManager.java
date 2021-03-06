/*    */ package mx.com.iecisa.commons.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class FileManager
/*    */ {
/*    */   public static void byteArrayToFile(byte[] file, String ruta) throws Exception {
/* 12 */     OutputStream out = null;
/*    */     try {
/* 14 */       out = new FileOutputStream(ruta);
/* 15 */       out.write(file);
/* 16 */       out.close();
/* 17 */     } catch (Exception e) {
/* 18 */       e.printStackTrace();
/*    */     } finally {
/* 20 */       if (out != null)
/* 21 */         out.close(); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static byte[] fileToByteArray(File file) throws Exception {
/* 26 */     FileInputStream fileInputStream = null;
/* 27 */     byte[] b = new byte[(int)file.length()];
/*    */     try {
/* 29 */       fileInputStream = new FileInputStream(file);
/* 30 */       fileInputStream.read(b);
/* 31 */       fileInputStream.close();
/* 32 */     } catch (IOException e1) {
/* 33 */       System.out.println("Error Reading The File.");
/* 34 */       e1.printStackTrace();
/*    */     } finally {
/* 36 */       if (fileInputStream != null) {
/* 37 */         fileInputStream.close();
/*    */       }
/*    */     } 
/* 40 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\mx\com\iecisa\common\\utils\FileManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */