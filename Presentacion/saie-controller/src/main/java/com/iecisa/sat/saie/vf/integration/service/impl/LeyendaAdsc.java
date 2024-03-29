/*     */ package com.iecisa.sat.saie.vf.integration.service.impl;

/*     */
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*     */ import com.itextpdf.text.Chunk;
/*     */ import com.itextpdf.text.Document;
/*     */ import com.itextpdf.text.DocumentException;
/*     */ import com.itextpdf.text.Font;
/*     */ import com.itextpdf.text.FontFactory;
/*     */ import com.itextpdf.text.Paragraph;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;

/*     */
/*     */
/*     */
/*     */ public class LeyendaAdsc/*     */ implements ILeyenda
/*     */ {
	/*     */ public void leyenda(Document pdf, Map<String, Object> vars) throws DocumentException {
		/* 22 */ String nombrealsc = (String) vars.getOrDefault("nombrealsc", "");
		/* 23 */ String fraccionalsc = (String) vars.getOrDefault("fraccionalsc", "");
		/* 24 */ Integer np = (Integer) vars.getOrDefault("np", Integer.valueOf(0));
		/* 25 */ VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO) vars.get("versiondto");
		/* 26 */ DatosEnrolamientoDTO datosdto = (DatosEnrolamientoDTO) vars.get("datosdto");
		/* 27 */ String ciudad = (String) vars.getOrDefault("ciudad", "");
		/* 28 */ String funcionario = (String) vars.getOrDefault("funcionario", "");
		/*     */
		/*     */
		/*     */
		/* 32 */ SimpleDateFormat sdf = new SimpleDateFormat("dd' de 'MMMM' de 'YYYY", new Locale("es", "MX"));
		/* 33 */ SimpleDateFormat sdf2 = new SimpleDateFormat("dd' días del mes de 'MMMM' del año 'YYYY",
				new Locale("es", "MX"));
		/*     */
		/* 35 */ int fuenteSize = 11;
		/* 36 */ Font fuente = FontFactory.getFont("Noto-Sans", fuenteSize);
		/* 37 */ Font fuenteBold = FontFactory.getFont("Noto-Sans", fuenteSize, 1);
		/*     */
		/*     */
		/*     */
		/* 41 */ Paragraph par = new Paragraph("", fuente);
		/* 42 */ par.setAlignment(3);
		/*     */
		/* 44 */ par.add(new Chunk(funcionario));
		/*     */
		/* 46 */ String texto = ", Administrador Desconcentrado de Servicios al Contribuyente de ";
		/* 47 */ par.add(new Chunk(texto));
		/*     */
		/* 49 */ par.add(new Chunk(nombrealsc));
		/*     */
		/* 51 */ texto = ", con fundamento en los artículos 63, cuarto párrafo del Código Fiscal de la Federación; 14, fracción II en relación con el 12, fracción XXIV, y 32 último párrafo numeral 10 del Reglamento Interior del Servicio de Administración Tributaria vigente, ";
		/*     */
		/*     */
		/*     */
		/* 55 */ par.add(new Chunk(texto));
		/*     */
		/* 57 */ par.add(new Chunk("CERTIFICO:", fuenteBold));
		/*     */
		/* 59 */ texto = " Que la presente representación impresa, que consta de " +
		/* 60 */ Math.round((np.intValue() / 2)) + " fojas del expediente de firma electrónica, corresponde" +
		/* 61 */ " al documento digital que obra en los sistemas electrónicos de esta unidad administrativa, mismo que "
				+
				/* 62 */ "se tuvo a la vista. La presente certificación se expide en ";
		/* 63 */ par.add(new Chunk(texto));
		/*     */
		/* 65 */ par.add(new Chunk(ciudad));
		/*     */
		/* 67 */ texto = " a los ";
		/* 68 */ par.add(new Chunk(texto));
		/*     */
		/* 70 */ String fecha = sdf2.format(new Date());
		/* 71 */ par.add(new Chunk(fecha));
		/*     */
		/* 73 */ texto = ".";
		/* 74 */ par.add(new Chunk(texto));
		/*     */
		/*     */
		/*     */
		/* 136 */ texto = "\n\n\n\n";
		/* 137 */ par.add(new Chunk(texto));
		/*     */
		/* 139 */ par.add(new Chunk(funcionario, fuenteBold));
		/*     */
		/* 141 */ texto = "\n";
		/* 142 */ par.add(new Chunk(texto, fuenteBold));
		/*     */
		/* 144 */ texto = "Administrador Desconcentrado de Servicios al Contribuyente de " + nombrealsc;
		/* 145 */ par.add(new Chunk(texto, fuenteBold));
		/*     */
		/* 147 */ pdf.add(par);
		/*     */ }
	/*     */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2019sep05_4.war!\WEB-INF\lib\visor-
 * documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\vf
 * \integration\service\impl\LeyendaAdsc.class Java compiler version: 8 (52.0)
 * JD-Core Version: 1.0.7
 */