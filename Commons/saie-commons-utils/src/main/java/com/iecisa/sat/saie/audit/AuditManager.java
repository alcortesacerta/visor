/*    */ package com.iecisa.sat.saie.audit;
/*    */ 
/*    */ import com.iecisa.sat.saie.audit.dto.AuditEvent;
/*    */ import java.text.SimpleDateFormat;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuditManager
/*    */ {
/* 12 */   private static final Logger logger = LoggerFactory.getLogger(AuditManager.class);
/* 13 */   private static final SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
/*    */   
/*    */   public static void register(AuditEvent event) {
/* 16 */     if (event != null)
/* 17 */       logger.info(String.format("%s, %s, %s, %s, %s, %s", new Object[] { event
/* 18 */               .getUuid(), event
/* 19 */               .getUser(), sdt
/* 20 */               .format(event.getDateTime()), event
/* 21 */               .getIpAddress(), event
/* 22 */               .getMacAddress(), event
/* 23 */               .getMessage() })); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\saie-commons-utils-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\audit\AuditManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */