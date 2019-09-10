/*    */ package mx.com.iecisa.commons.utils;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
/*    */ import javax.xml.datatype.DatatypeFactory;
/*    */ import javax.xml.datatype.XMLGregorianCalendar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DateFormatter
/*    */ {
/*    */   public static String dateToString(Date date, String format) {
/* 20 */     String result = null;
/* 21 */     SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
/*    */     try {
/* 23 */       if (date != null) {
/* 24 */         result = dateFormatter.format(date);
/*    */       }
/* 26 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 29 */     return result;
/*    */   }
/*    */   
/*    */   public static Date stringToDate(String date, String format) {
/* 33 */     Date result = null;
/*    */     try {
/* 35 */       SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
/* 36 */       result = dateFormatter.parse(date);
/* 37 */     } catch (IllegalArgumentException illegalArgumentException) {
/*    */     
/* 39 */     } catch (ParseException parseException) {
/*    */     
/* 41 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 44 */     return result;
/*    */   }
/*    */   
/*    */   public static Date toDate(XMLGregorianCalendar calendar) {
/* 48 */     if (calendar == null) {
/* 49 */       return null;
/*    */     }
/* 51 */     return calendar.toGregorianCalendar().getTime();
/*    */   }
/*    */   
/*    */   public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
/* 55 */     XMLGregorianCalendar xmlCalendar = null;
/*    */     
/* 57 */     if (date != null) {
/* 58 */       GregorianCalendar gCalendar = new GregorianCalendar();
/* 59 */       gCalendar.setTime(date);
/*    */       try {
/* 61 */         xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
/* 62 */       } catch (Exception exception) {}
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 67 */     return xmlCalendar;
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\mx\com\iecisa\common\\utils\DateFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */