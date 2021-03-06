/*     */ package com.iecisa.sat.saie.dao;
/*     */ 
/*     */ import com.iecisa.sat.saie.audit.dto.DBSaie;
/*     */ import com.iecisa.sat.saie.audit.dto.DBSaieResult;
/*     */ import com.zaxxer.hikari.HikariConfig;
/*     */ import com.zaxxer.hikari.HikariDataSource;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class DBManagerSaie
/*     */ {
/*  20 */   private static DBManagerSaie instance = null;
/*     */   
/*  22 */   private HikariDataSource dataSourceVisor = null;
/*  23 */   private HikariDataSource dataSourceDuplicidad = null;
/*  24 */   private HikariDataSource dataSourceExterna = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private static Connection getConnection(DBSaie db) throws SQLException {
/*  29 */     if (instance == null) {
/*  30 */       instance = new DBManagerSaie();
/*     */     }
/*  32 */     if (db == DBSaie.VISOR) {
/*  33 */       if (instance.dataSourceVisor == null) {
/*  34 */         HikariConfig config = new HikariConfig();
/*  35 */         config.setJdbcUrl(System.getProperty("SAIE_JDBC"));
/*  36 */         config.setUsername(System.getProperty("SAIE_DB_USER"));
/*  37 */         config.setPassword(System.getProperty("SAIE_DB_PASS"));
/*  38 */         config.setMaximumPoolSize(Integer.parseInt(System.getProperty("SAIE_DB_MAXPOOLSIZE")));
/*  39 */         config.addDataSourceProperty("cachePrepStmts", "true");
/*  40 */         config.addDataSourceProperty("prepStmtCacheSize", "1");
/*  41 */         config.addDataSourceProperty("prepStmtCacheSqlLimit", "5");
/*     */         
/*  43 */         instance.dataSourceVisor = new HikariDataSource(config);
/*     */       } 
/*  45 */       return instance.dataSourceVisor.getConnection();
/*  46 */     }  if (db == DBSaie.DUPLICIDAD) {
/*  47 */       if (instance.dataSourceDuplicidad == null) {
/*  48 */         HikariConfig config = new HikariConfig();
/*  49 */         config.setJdbcUrl(System.getProperty("SAIE_DUPLICIDAD_JDBC"));
/*  50 */         config.setUsername(System.getProperty("SAIE_DUPLICIDAD_DB_USER"));
/*  51 */         config.setPassword(System.getProperty("SAIE_DUPLICIDAD_DB_PASS"));
/*  52 */         config.setMaximumPoolSize(Integer.parseInt(System.getProperty("SAIE_DB_MAXPOOLSIZE")));
/*  53 */         config.addDataSourceProperty("cachePrepStmts", "true");
/*  54 */         config.addDataSourceProperty("prepStmtCacheSize", "1");
/*  55 */         config.addDataSourceProperty("prepStmtCacheSqlLimit", "5");
/*     */         
/*  57 */         instance.dataSourceDuplicidad = new HikariDataSource(config);
/*     */       } 
/*  59 */       return instance.dataSourceDuplicidad.getConnection();
/*     */     } 
/*  61 */     if (instance.dataSourceExterna == null) {
/*  62 */       HikariConfig config = new HikariConfig();
/*  63 */       config.setJdbcUrl(System.getProperty("SAIE_EXTERNA_JDBC"));
/*  64 */       config.setUsername(System.getProperty("SAIE_EXTERNA_DB_USER"));
/*  65 */       config.setPassword(System.getProperty("SAIE_EXTERNA_DB_PASS"));
/*  66 */       config.setMaximumPoolSize(Integer.parseInt(System.getProperty("SAIE_DB_MAXPOOLSIZE")));
/*  67 */       config.addDataSourceProperty("cachePrepStmts", "true");
/*  68 */       config.addDataSourceProperty("prepStmtCacheSize", "1");
/*  69 */       config.addDataSourceProperty("prepStmtCacheSqlLimit", "5");
/*     */       
/*  71 */       instance.dataSourceExterna = new HikariDataSource(config);
/*     */     } 
/*  73 */     return instance.dataSourceExterna.getConnection();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void closePool() {
/*  78 */     if (instance.dataSourceVisor != null) {
/*  79 */       instance.dataSourceVisor.close();
/*  80 */       instance.dataSourceVisor = null;
/*     */     } 
/*  82 */     if (instance.dataSourceDuplicidad != null) {
/*  83 */       instance.dataSourceDuplicidad.close();
/*  84 */       instance.dataSourceDuplicidad = null;
/*     */     } 
/*  86 */     if (instance.dataSourceExterna != null) {
/*  87 */       instance.dataSourceExterna.close();
/*  88 */       instance.dataSourceExterna = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void llenaPreparedStatement(PreparedStatement ps, List<Object> args) throws SQLException {
/*  93 */     if (args != null) {
/*  94 */       for (int i = 0; i < args.size(); i++) {
/*  95 */         ps.setObject(i + 1, args.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static DBSaieResult select(String sql, DBSaie db, List<Object> args) throws SQLException {
/* 101 */     try(Connection connection = getConnection(db); 
/* 102 */         PreparedStatement ps = connection.prepareStatement(sql)) {
/* 103 */       List<Map<String, Object>> resultados = new ArrayList<Map<String, Object>>();
/*     */       
/* 105 */       llenaPreparedStatement(ps, args);
/*     */       
/* 107 */       if (ps.execute()) {
/* 108 */         ResultSet rs = ps.getResultSet();
/*     */         
/* 110 */         ResultSetMetaData rsmd = rs.getMetaData();
/*     */         
/* 112 */         while (rs.next()) {
/* 113 */           Map<String, Object> row = new HashMap<String, Object>();
/* 114 */           for (int i = 1; i <= rsmd.getColumnCount(); i++) {
/* 115 */             row.put(rsmd.getColumnName(i), (rs.getObject(rsmd.getColumnName(i)) != null) ? rs.getObject(rsmd.getColumnName(i)) : "");
/*     */           }
/* 117 */           resultados.add(row);
/*     */         } 
/*     */       } 
/* 120 */       return new DBSaieResult(resultados);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void insertUpdate(String sql, DBSaie db, List<Object> args) throws SQLException {
/* 125 */     try(Connection connection = getConnection(db); 
/* 126 */         PreparedStatement ps = connection.prepareStatement(sql)) {
/* 127 */       llenaPreparedStatement(ps, args);
/* 128 */       connection.setAutoCommit(false);
/* 129 */       ps.executeUpdate();
/* 130 */       connection.commit();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void insertUpdateTransaction(List<String> sql, DBSaie db, List<List<Object>> argsList) throws SQLException {
/* 135 */     Connection connection = null;
/*     */     try {
/* 137 */       connection = getConnection(db);
/* 138 */       connection.setAutoCommit(false);
/* 139 */       for (int i = 0; i < sql.size(); i++) {
/* 140 */         try (PreparedStatement ps = connection.prepareStatement((String)sql.get(i))) {
/* 141 */           llenaPreparedStatement(ps, (List)argsList.get(i));
/* 142 */           ps.executeUpdate();
/*     */         } 
/*     */       } 
/* 145 */       connection.commit();
/* 146 */     } catch (Exception e) {
/* 147 */       e.printStackTrace();
/* 148 */       if (connection != null) {
/* 149 */         connection.rollback();
/*     */       }
/*     */     } finally {
/* 152 */       if (connection != null)
/* 153 */         connection.close(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\saie-commons-utils-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\dao\DBManagerSaie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */