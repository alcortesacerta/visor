/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */ import java.util.List;

/*    */
/*    */
/*    */ public class VersionEnrolamientoAdjuntosDTO
/*    */ {
	/*    */ private List<ArchivoDTO> huellas;
	/*    */ private List<ArchivoDTO> iris;
	/*    */ private List<ArchivoDTO> documentos;

	/*    */
	/* 12 */ public List<ArchivoDTO> getHuellas() {
		return this.huellas;
	}

	/*    */
	/*    */
	/* 15 */ public void setHuellas(List<ArchivoDTO> huellas) {
		this.huellas = huellas;
	}

	/*    */
	/*    */
	/* 18 */ public List<ArchivoDTO> getIris() {
		return this.iris;
	}

	/*    */
	/*    */
	/* 21 */ public void setIris(List<ArchivoDTO> iris) {
		this.iris = iris;
	}

	/*    */
	/*    */
	/* 24 */ public List<ArchivoDTO> getDocumentos() {
		return this.documentos;
	}

	/*    */
	/*    */
	/* 27 */ public void setDocumentos(List<ArchivoDTO> documentos) {
		this.documentos = documentos;
	}

	/*    */
	/*    */
	/*    */
	/*    */ public String toString() {
		/* 32 */ StringBuilder builder = new StringBuilder();
		/* 33 */ builder.append("VersionEnrolamientoAdjuntosDTO [");
		/* 34 */ if (this.huellas != null) {
			/* 35 */ builder.append("huellas=");
			/* 36 */ builder.append(this.huellas);
			/* 37 */ builder.append(", ");
			/*    */ }
		/* 39 */ if (this.iris != null) {
			/* 40 */ builder.append("iris=");
			/* 41 */ builder.append(this.iris);
			/* 42 */ builder.append(", ");
			/*    */ }
		/* 44 */ if (this.documentos != null) {
			/* 45 */ builder.append("documentos=");
			/* 46 */ builder.append(this.documentos);
			/*    */ }
		/* 48 */ builder.append("]");
		/* 49 */ return builder.toString();
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\VersionEnrolamientoAdjuntosDTO.class Java
 * compiler version: 8 (52.0) JD-Core Version: 1.0.7
 */