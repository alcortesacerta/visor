/*    */ package com.iecisa.sat.saie.response;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServiceResponse<T>
/*    */   extends Object
/*    */ {
/* 10 */   private int code = 0;
/* 11 */   private String message = "OK";
/*    */   
/*    */   private T result;
/*    */   
/* 15 */   public int getCode() { return this.code; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public void setCode(int code) { this.code = code; }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public String getMessage() { return this.message; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void setMessage(String message) { this.message = message; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public T getResult() { return (T)this.result; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setResult(T result) { this.result = result; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     StringBuilder builder = new StringBuilder();
/* 41 */     builder.append("ServiceResponse [code=");
/* 42 */     builder.append(this.code);
/* 43 */     builder.append(", ");
/* 44 */     if (this.message != null) {
/* 45 */       builder.append("message=");
/* 46 */       builder.append(this.message);
/* 47 */       builder.append(", ");
/*    */     } 
/* 49 */     if (this.result != null) {
/* 50 */       builder.append("result=");
/* 51 */       builder.append(this.result);
/*    */     } 
/* 53 */     builder.append("]");
/* 54 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\saie-commons-utils-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\response\ServiceResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */