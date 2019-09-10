/*     */ package com.saie.beans;
/*     */ import com.saie.beans.Contribuyente;
/*     */ 
/*     */ public class Contribuyente {
/*   5 */   String nombre = "Pedro";
/*   6 */   String apellidos = "Alvarez Andante";
/*   7 */   String foto = "foto01.jpg";
/*   8 */   String fecha = "12/05/75";
/*   9 */   String lugar = "Puebla, Pue.";
/*  10 */   String sexo = "M";
/*  11 */   String curp = "AACP640301IP2HJRT";
/*  12 */   String fechaEnrolamiento = "02/04/15";
/*  13 */   String estadoTramite = "aprobado";
/*  14 */   String fechaCarga = "02/04/15 15:30";
/*  15 */   String fechaAbis = "02/10/15 14:45";
/*  16 */   String rfc = "AACP640301IP2";
/*     */ 
/*     */ 
/*     */   
/*     */   public Contribuyente() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Contribuyente(String nombre, String apellidos, String lugar, String fechaEnrolamiento, String rfc) {
/*  25 */     this.nombre = nombre;
/*  26 */     this.apellidos = apellidos;
/*  27 */     this.lugar = lugar;
/*  28 */     this.fechaEnrolamiento = fechaEnrolamiento;
/*  29 */     this.rfc = rfc;
/*     */   }
/*     */   
/*  32 */   public String getNombre() { return this.nombre; }
/*     */ 
/*     */   
/*  35 */   public void setNombre(String nombre) { this.nombre = nombre; }
/*     */ 
/*     */   
/*  38 */   public String getApellidos() { return this.apellidos; }
/*     */ 
/*     */   
/*  41 */   public void setApellidos(String apellidos) { this.apellidos = apellidos; }
/*     */ 
/*     */   
/*  44 */   public String getFoto() { return this.foto; }
/*     */ 
/*     */   
/*  47 */   public void setFoto(String foto) { this.foto = foto; }
/*     */ 
/*     */   
/*  50 */   public String getFecha() { return this.fecha; }
/*     */ 
/*     */   
/*  53 */   public void setFecha(String fecha) { this.fecha = fecha; }
/*     */ 
/*     */   
/*  56 */   public String getLugar() { return this.lugar; }
/*     */ 
/*     */   
/*  59 */   public void setLugar(String lugar) { this.lugar = lugar; }
/*     */ 
/*     */   
/*  62 */   public String getSexo() { return this.sexo; }
/*     */ 
/*     */   
/*  65 */   public void setSexo(String sexo) { this.sexo = sexo; }
/*     */ 
/*     */   
/*  68 */   public String getCurp() { return this.curp; }
/*     */ 
/*     */   
/*  71 */   public void setCurp(String curp) { this.curp = curp; }
/*     */ 
/*     */   
/*  74 */   public String getFechaEnrolamiento() { return this.fechaEnrolamiento; }
/*     */ 
/*     */   
/*  77 */   public void setFechaEnrolamiento(String fechaEnrolamiento) { this.fechaEnrolamiento = fechaEnrolamiento; }
/*     */ 
/*     */   
/*  80 */   public String getEstadoTramite() { return this.estadoTramite; }
/*     */ 
/*     */   
/*  83 */   public void setEstadoTramite(String estadoTramite) { this.estadoTramite = estadoTramite; }
/*     */ 
/*     */   
/*  86 */   public String getFechaCarga() { return this.fechaCarga; }
/*     */ 
/*     */   
/*  89 */   public void setFechaCarga(String fechaCarga) { this.fechaCarga = fechaCarga; }
/*     */ 
/*     */   
/*  92 */   public String getFechaAbis() { return this.fechaAbis; }
/*     */ 
/*     */   
/*  95 */   public void setFechaAbis(String fechaAbis) { this.fechaAbis = fechaAbis; }
/*     */ 
/*     */   
/*  98 */   public String getRfc() { return this.rfc; }
/*     */ 
/*     */   
/* 101 */   public void setRfc(String rfc) { this.rfc = rfc; }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\classes\com\saie\beans\Contribuyente.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */