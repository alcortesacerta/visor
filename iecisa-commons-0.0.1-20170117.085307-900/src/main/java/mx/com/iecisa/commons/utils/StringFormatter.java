/*    */ package mx.com.iecisa.commons.utils;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringFormatter
/*    */ {
/* 16 */   public static <T> String leftPad(T cad, char padding, int size) { return String.format("%" + size + "s", new Object[] { cad }).replace(' ', padding); }
/*    */ 
/*    */   
/*    */   public static <T> String join(Collection<T> list, String separator, String startEndDelimiter) {
/* 20 */     String result = null;
/*    */     
/* 22 */     if (list != null && !list.isEmpty()) {
/* 23 */       result = "";
/* 24 */       result = result + ((startEndDelimiter != null) ? startEndDelimiter : "");
/*    */       
/* 26 */       if (list.size() > 1) {
/* 27 */         result = result + StringUtils.join(list, separator);
/*    */       } else {
/* 29 */         result = result + list.toArray()[0];
/*    */       } 
/*    */       
/* 32 */       result = result + ((startEndDelimiter != null) ? startEndDelimiter : "");
/*    */     } 
/*    */ 
/*    */     
/* 36 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\mx\com\iecisa\common\\utils\StringFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */