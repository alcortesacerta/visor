/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */
/*    */
/*    */ public class CatalogoDTO
/*    */ {
	/*    */ private String valor;
	/*    */ private int id;

	/*    */
	/*    */ public CatalogoDTO() {
	}

	/*    */
	/*    */ public CatalogoDTO(String valor, int id) {
		/* 13 */ this.valor = valor;
		/* 14 */ this.id = id;
		/*    */ }

	/*    */
	/* 17 */ public String getValor() {
		return this.valor;
	}

	/*    */
	/*    */
	/* 20 */ public void setValor(String valor) {
		this.valor = valor;
	}

	/*    */
	/*    */
	/* 23 */ public int getId() {
		return this.id;
	}

	/*    */
	/*    */
	/* 26 */ public void setId(int id) {
		this.id = id;
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\CatalogoDTO.class Java compiler version: 8
 * (52.0) JD-Core Version: 1.0.7
 */