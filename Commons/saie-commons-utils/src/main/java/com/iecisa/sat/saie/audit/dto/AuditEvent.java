/*     */ package com.iecisa.sat.saie.audit.dto;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AuditEvent
/*     */ {
/*     */   private EventType type;
/*     */   private String message;
/*     */   private String module;
/*     */   private String ipAddress;
/*     */   private String macAddress;
/*     */   private String user;
/*     */   private String uuid;
/*     */   private Date dateTime;
/*     */   
/*     */   public AuditEvent() {}
/*     */   
/*     */   public AuditEvent(String uuid, Date dateTime, String user, String ipAddress, String macAddress) {
/*  24 */     this.ipAddress = ipAddress;
/*  25 */     this.macAddress = macAddress;
/*  26 */     this.user = user;
/*  27 */     this.uuid = uuid;
/*  28 */     this.dateTime = dateTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AuditEvent(EventType type, Date dateTime, String ipAddress, String user) {
/*  34 */     this.type = type;
/*  35 */     this.dateTime = dateTime;
/*  36 */     this.ipAddress = ipAddress;
/*  37 */     this.user = user;
/*     */   }
/*     */   
/*     */   public AuditEvent(EventType type, String ipAddress, String user) {
/*  41 */     this.type = type;
/*  42 */     this.ipAddress = ipAddress;
/*  43 */     this.user = user;
/*     */   }
/*     */   
/*     */   public AuditEvent(EventType type, String ipAddress, String user, String module, String message) {
/*  47 */     this.type = type;
/*  48 */     this.ipAddress = ipAddress;
/*  49 */     this.user = user;
/*  50 */     this.module = module;
/*  51 */     this.message = message;
/*     */   }
/*     */ 
/*     */   
/*  55 */   public EventType getType() { return this.type; }
/*     */ 
/*     */   
/*  58 */   public void setType(EventType type) { this.type = type; }
/*     */ 
/*     */   
/*  61 */   public String getMessage() { return this.message; }
/*     */ 
/*     */   
/*  64 */   public void setMessage(String message) { this.message = message; }
/*     */ 
/*     */   
/*  67 */   public String getModule() { return this.module; }
/*     */ 
/*     */   
/*  70 */   public void setModule(String module) { this.module = module; }
/*     */ 
/*     */   
/*  73 */   public String getIpAddress() { return this.ipAddress; }
/*     */ 
/*     */   
/*  76 */   public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
/*     */ 
/*     */   
/*  79 */   public String getUser() { return this.user; }
/*     */ 
/*     */   
/*  82 */   public void setUser(String user) { this.user = user; }
/*     */ 
/*     */   
/*  85 */   public Date getDateTime() { return this.dateTime; }
/*     */ 
/*     */   
/*  88 */   public void setDateTime(Date dateTime) { this.dateTime = dateTime; }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  93 */     StringBuilder builder = new StringBuilder();
/*  94 */     builder.append("AuditEvent [");
/*  95 */     if (this.type != null) {
/*  96 */       builder.append("type=");
/*  97 */       builder.append(this.type);
/*  98 */       builder.append(", ");
/*     */     } 
/* 100 */     if (this.message != null) {
/* 101 */       builder.append("message=");
/* 102 */       builder.append(this.message);
/* 103 */       builder.append(", ");
/*     */     } 
/* 105 */     if (this.module != null) {
/* 106 */       builder.append("module=");
/* 107 */       builder.append(this.module);
/* 108 */       builder.append(", ");
/*     */     } 
/* 110 */     if (this.ipAddress != null) {
/* 111 */       builder.append("ipAddress=");
/* 112 */       builder.append(this.ipAddress);
/* 113 */       builder.append(", ");
/*     */     } 
/* 115 */     if (this.user != null) {
/* 116 */       builder.append("user=");
/* 117 */       builder.append(this.user);
/* 118 */       builder.append(", ");
/*     */     } 
/* 120 */     if (this.dateTime != null) {
/* 121 */       builder.append("dateTime=");
/* 122 */       builder.append(this.dateTime);
/*     */     } 
/* 124 */     builder.append("]");
/* 125 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/* 129 */   public String getMacAddress() { return this.macAddress; }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public void setMacAddress(String macAddress) { this.macAddress = macAddress; }
/*     */ 
/*     */ 
/*     */   
/* 137 */   public String getUuid() { return this.uuid; }
/*     */ 
/*     */ 
/*     */   
/* 141 */   public void setUuid(String uuid) { this.uuid = uuid; }
/*     */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\saie-commons-utils-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\audit\dto\AuditEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */