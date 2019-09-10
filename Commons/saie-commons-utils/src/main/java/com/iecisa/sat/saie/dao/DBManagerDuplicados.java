/*    */ package com.iecisa.sat.saie.dao;
/*    */ 
/*    */ import com.zaxxer.hikari.HikariConfig;
/*    */ import com.zaxxer.hikari.HikariDataSource;
/*    */ 
/*    */ public class DBManagerDuplicados
/*    */ {
/*  8 */   private static DBManagerDuplicados instance = null;
/*    */   
/* 10 */   public HikariDataSource ds = null;
/*    */   
/*    */   public static DBManagerDuplicados getInstance() {
/* 13 */     if (instance == null) {
/* 14 */       HikariConfig config = new HikariConfig();
/* 15 */       config.setJdbcUrl(System.getProperty("SAIE_DUPLICIDAD_JDBC"));
/* 16 */       config.setUsername(System.getProperty("SAIE_DUPLICIDAD_DB_USER"));
/* 17 */       config.setPassword(System.getProperty("SAIE_DUPLICIDAD_DB_PASS"));
/* 18 */       config.setMaximumPoolSize(Integer.parseInt(System.getProperty("SAIE_DB_COLA_MAXPOOLSIZE")));
/* 19 */       config.addDataSourceProperty("cachePrepStmts", "true");
/* 20 */       config.addDataSourceProperty("prepStmtCacheSize", "1");
/* 21 */       config.addDataSourceProperty("prepStmtCacheSqlLimit", "5");
/*    */       
/* 23 */       instance = new DBManagerDuplicados();
/* 24 */       instance.ds = new HikariDataSource(config);
/*    */     } 
/*    */     
/* 27 */     return instance;
/*    */   }
/*    */   
/*    */   public static void closePool() {
/* 31 */     if (instance.ds != null) {
/* 32 */       instance.ds.close();
/* 33 */       instance.ds = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\saie-commons-utils-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\dao\DBManagerDuplicados.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */