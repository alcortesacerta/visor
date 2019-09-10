/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */
/*    */ public class BuscarEnrolamientoPorCriteriosDTO
/*    */ {
	/*    */ private String rfc;
	/*    */ private String curp;
	/*    */ private String nombre;
	/*    */ private String aPaterno;
	/*    */ private String aMaterno;

	/*    */
	/* 12 */ public String getRfc() {
		return this.rfc;
	}

	/*    */
	/*    */
	/* 15 */ public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/*    */
	/*    */
	/* 18 */ public String getCurp() {
		return this.curp;
	}

	/*    */
	/*    */
	/* 21 */ public void setCurp(String curp) {
		this.curp = curp;
	}

	/*    */
	/*    */
	/* 24 */ public String getNombre() {
		return this.nombre;
	}

	/*    */
	/*    */
	/* 27 */ public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*    */
	/*    */
	/* 30 */ public String getaPaterno() {
		return this.aPaterno;
	}

	/*    */
	/*    */
	/* 33 */ public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	/*    */
	/*    */
	/* 36 */ public String getaMaterno() {
		return this.aMaterno;
	}

	/*    */
	/*    */
	/* 39 */ public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	/*    */
	/*    */
	/*    */
	/*    */ public String toString() {
		/* 44 */ StringBuilder builder = new StringBuilder();
		/* 45 */ builder.append("BuscarEnrolamientoPorCriteriosDTO [");
		/* 46 */ if (this.rfc != null) {
			/* 47 */ builder.append("rfc=");
			/* 48 */ builder.append(this.rfc);
			/* 49 */ builder.append(", ");
			/*    */ }
		/* 51 */ if (this.curp != null) {
			/* 52 */ builder.append("curp=");
			/* 53 */ builder.append(this.curp);
			/* 54 */ builder.append(", ");
			/*    */ }
		/* 56 */ if (this.nombre != null) {
			/* 57 */ builder.append("nombre=");
			/* 58 */ builder.append(this.nombre);
			/* 59 */ builder.append(", ");
			/*    */ }
		/* 61 */ if (this.aPaterno != null) {
			/* 62 */ builder.append("aPaterno=");
			/* 63 */ builder.append(this.aPaterno);
			/* 64 */ builder.append(", ");
			/*    */ }
		/* 66 */ if (this.aMaterno != null) {
			/* 67 */ builder.append("aMaterno=");
			/* 68 */ builder.append(this.aMaterno);
			/*    */ }
		/* 70 */ builder.append("]");
		/* 71 */ return builder.toString();
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\BuscarEnrolamientoPorCriteriosDTO.class Java
 * compiler version: 8 (52.0) JD-Core Version: 1.0.7
 */