/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */ public class ConsultaSiseDTO
/*    */ {
	/*    */ private String code;
	/*    */ private String message;
	/*    */ private String result;

	/*    */
	/* 9 */ public String getCode() {
		return this.code;
	}

	/*    */
	/*    */
	/* 12 */ public void setCode(String code) {
		this.code = code;
	}

	/*    */
	/*    */
	/* 15 */ public String getMessage() {
		return this.message;
	}

	/*    */
	/*    */
	/* 18 */ public void setMessage(String message) {
		this.message = message;
	}

	/*    */
	/*    */
	/* 21 */ public String getResult() {
		return this.result;
	}

	/*    */
	/*    */
	/* 24 */ public void setResult(String result) {
		this.result = result;
	}

	/*    */
	/*    */
	/*    */
	/*    */
	/* 29 */ public String toString() {
		return "ConsultaSiseDTO [" + ((this.code != null) ? ("code=" + this.code + ", ") : "")
				+ ((this.message != null) ? ("message=" + this.message + ", ") : "")
				+ ((this.result != null) ? ("result=" + this.result) : "") + "]";
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\ConsultaSiseDTO.class Java compiler version:
 * 8 (52.0) JD-Core Version: 1.0.7
 */