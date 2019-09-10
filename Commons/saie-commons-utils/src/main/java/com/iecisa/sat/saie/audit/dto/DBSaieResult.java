/*    */ package com.iecisa.sat.saie.audit.dto;
/*    */ 
/*    */ import java.sql.Timestamp;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DBSaieResult {
/*    */   public DBSaieResult(List<Map<String, Object>> resultSet) {
/* 10 */     this.resultSet = new ArrayList();
/*    */ 
/*    */ 
/*    */     
/* 14 */     this.resultSet = resultSet;
/*    */   }
/*    */   private List<Map<String, Object>> resultSet;
/*    */   public String getString(int indice, String nombre) {
/* 18 */     if (indice <= this.resultSet.size()) {
/* 19 */       Map<String, Object> temp = (Map)this.resultSet.get(indice);
/* 20 */       if (temp != null) {
/* 21 */         return temp.get(nombre).toString();
/*    */       }
/*    */     } 
/* 24 */     return null;
/*    */   }
/*    */   
/*    */   public int getInt(int indice, String nombre) {
/* 28 */     if (indice <= this.resultSet.size()) {
/* 29 */       Map<String, Object> temp = (Map)this.resultSet.get(indice);
/* 30 */       if (temp != null) {
/*    */         try {
/* 32 */           return Integer.parseInt(temp.get(nombre).toString());
/* 33 */         } catch (Exception e) {
/* 34 */           return -1;
/*    */         } 
/*    */       }
/*    */     } 
/* 38 */     return -1;
/*    */   }
/*    */   
/*    */   public boolean getBoolean(int indice, String nombre) {
/* 42 */     if (indice <= this.resultSet.size()) {
/* 43 */       Map<String, Object> temp = (Map)this.resultSet.get(indice);
/* 44 */       if (temp != null) {
/*    */         try {
/* 46 */           return Boolean.parseBoolean(temp.get(nombre).toString());
/* 47 */         } catch (Exception e) {
/* 48 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   public Timestamp getTimestamp(int indice, String nombre) {
/* 56 */     if (indice <= this.resultSet.size()) {
/* 57 */       Map<String, Object> temp = (Map)this.resultSet.get(indice);
/* 58 */       if (temp != null) {
/*    */         try {
/* 60 */           return (Timestamp)temp.get(nombre);
/* 61 */         } catch (Exception e) {
/* 62 */           return null;
/*    */         } 
/*    */       }
/*    */     } 
/* 66 */     return null;
/*    */   }
/*    */ 
/*    */   
/* 70 */   public int size() { return this.resultSet.size(); }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\saie-commons-utils-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\audit\dto\DBSaieResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */