/*    */ package com.iecisa.commons.dto;
/*    */ 
/*    */ public class CatalogoInfoLocaleDTO<T>
/*    */   extends Object
/*    */ {
/*    */   private T lang;
/*    */   private String nombre;
/*    */   private String desc;
/*    */   
/* 10 */   public T getLang() { return (T)this.lang; }
/*    */ 
/*    */   
/* 13 */   public void setLang(T lang) { this.lang = lang; }
/*    */ 
/*    */   
/* 16 */   public String getNombre() { return this.nombre; }
/*    */ 
/*    */   
/* 19 */   public void setNombre(String nombre) { this.nombre = nombre; }
/*    */ 
/*    */   
/* 22 */   public String getDesc() { return this.desc; }
/*    */ 
/*    */   
/* 25 */   public void setDesc(String desc) { this.desc = desc; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     StringBuilder builder = new StringBuilder();
/* 31 */     builder.append("CatalogoInfoLocaleDTO [");
/* 32 */     if (this.lang != null) {
/* 33 */       builder.append("lang=");
/* 34 */       builder.append(this.lang);
/* 35 */       builder.append(", ");
/*    */     } 
/* 37 */     if (this.nombre != null) {
/* 38 */       builder.append("nombre=");
/* 39 */       builder.append(this.nombre);
/* 40 */       builder.append(", ");
/*    */     } 
/* 42 */     if (this.desc != null) {
/* 43 */       builder.append("desc=");
/* 44 */       builder.append(this.desc);
/*    */     } 
/* 46 */     builder.append("]");
/* 47 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\com\iecisa\commons\dto\CatalogoInfoLocaleDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */