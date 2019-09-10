/*    */ package com.iecisa.commons.dto;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class CatalogoGenericoDTO<T>
/*    */   extends Object
/*    */ {
/*    */   protected T id;
/*    */   protected Boolean estado;
/*    */   protected List<CatalogoInfoLocaleDTO<String>> locale;
/*    */   
/* 12 */   public T getId() { return (T)this.id; }
/*    */ 
/*    */   
/* 15 */   public void setId(T id) { this.id = id; }
/*    */ 
/*    */   
/* 18 */   public Boolean getEstado() { return this.estado; }
/*    */ 
/*    */   
/* 21 */   public void setEstado(Boolean estado) { this.estado = estado; }
/*    */ 
/*    */   
/* 24 */   public List<CatalogoInfoLocaleDTO<String>> getLocale() { return this.locale; }
/*    */ 
/*    */   
/* 27 */   public void setLocale(List<CatalogoInfoLocaleDTO<String>> locale) { this.locale = locale; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     StringBuilder builder = new StringBuilder();
/* 33 */     builder.append("CatalogoGenericoDTO [");
/* 34 */     if (this.id != null) {
/* 35 */       builder.append("id=");
/* 36 */       builder.append(this.id);
/* 37 */       builder.append(", ");
/*    */     } 
/* 39 */     if (this.estado != null) {
/* 40 */       builder.append("estado=");
/* 41 */       builder.append(this.estado);
/* 42 */       builder.append(", ");
/*    */     } 
/* 44 */     if (this.locale != null) {
/* 45 */       builder.append("locale=");
/* 46 */       builder.append(this.locale);
/*    */     } 
/* 48 */     builder.append("]");
/* 49 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\com\iecisa\commons\dto\CatalogoGenericoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */