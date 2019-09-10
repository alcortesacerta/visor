/*    */ package com.iecisa.commons.dto;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomCriteriaDTO<T>
/*    */   extends Object
/*    */ {
/*    */   private boolean countEnabled;
/*    */   private Integer firstResult;
/*    */   private Integer maxResults;
/*    */   private T criteria;
/*    */   
/* 18 */   public boolean isCountEnabled() { return this.countEnabled; }
/*    */ 
/*    */   
/* 21 */   public void setCountEnabled(boolean countEnabled) { this.countEnabled = countEnabled; }
/*    */ 
/*    */   
/* 24 */   public Integer getFirstResult() { return this.firstResult; }
/*    */ 
/*    */   
/* 27 */   public void setFirstResult(Integer firstResult) { this.firstResult = firstResult; }
/*    */ 
/*    */   
/* 30 */   public Integer getMaxResults() { return this.maxResults; }
/*    */ 
/*    */   
/* 33 */   public void setMaxResults(Integer maxResults) { this.maxResults = maxResults; }
/*    */ 
/*    */   
/* 36 */   public T getCriteria() { return (T)this.criteria; }
/*    */ 
/*    */   
/* 39 */   public void setCriteria(T criteria) { this.criteria = criteria; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     StringBuilder builder = new StringBuilder();
/* 45 */     builder.append("CustomCriteriaDTO [countEnabled=");
/* 46 */     builder.append(this.countEnabled);
/* 47 */     builder.append(", ");
/* 48 */     if (this.firstResult != null) {
/* 49 */       builder.append("firstResult=");
/* 50 */       builder.append(this.firstResult);
/* 51 */       builder.append(", ");
/*    */     } 
/* 53 */     if (this.maxResults != null) {
/* 54 */       builder.append("maxResults=");
/* 55 */       builder.append(this.maxResults);
/* 56 */       builder.append(", ");
/*    */     } 
/* 58 */     if (this.criteria != null) {
/* 59 */       builder.append("criteria=");
/* 60 */       builder.append(this.criteria);
/*    */     } 
/* 62 */     builder.append("]");
/* 63 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\com\iecisa\commons\dto\CustomCriteriaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */