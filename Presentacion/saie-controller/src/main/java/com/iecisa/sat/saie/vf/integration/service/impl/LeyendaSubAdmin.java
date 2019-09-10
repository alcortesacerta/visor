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
/*     */ public class LeyendaSubAdmin/*     */ implements ILeyenda
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
		/* 46 */ String texto = ", Subadministrador de Servicios al Contribuyente de ";
		/* 47 */ par.add(new Chunk(texto));
		/*     */
		/* 49 */ par.add(new Chunk(nombrealsc));
		/*     */
		/* 51 */ texto = ", con fundamento en los artículos 63, cuarto párrafo del Código Fiscal de la Federación; 15, fracción II en relación con el 12, fracción XXIV, y 32 ";
		/*     */
		/* 53 */ par.add(new Chunk(texto));
		/*     */
		/* 55 */ par.add(new Chunk(fraccionalsc));
		/*     */
		/* 57 */ texto = /* 58 */ " del Reglamento Interior del Servicio de Administración Tributaria vigente, ";
		/*     */
		/* 60 */ par.add(new Chunk(texto));
		/*     */
		/* 62 */ par.add(new Chunk("CERTIFICO:", fuenteBold));
		/*     */
		/* 64 */ texto = " Que la presente representación impresa, que consta de " +
		/* 65 */ Math.round((np.intValue() / 2))
				+ " fojas, del expediente de firma electrónica, corresponde al documento digital que obra en los " +
				/* 66 */ " sistemas electrónicos de esta unidad administrativa, mismo que se tuvo a la vista. La presente certificación se expide "
				+
				/* 67 */ "en ";
		/* 68 */ par.add(new Chunk(texto));
		/*     */
		/* 70 */ par.add(new Chunk(ciudad));
		/*     */
		/* 72 */ texto = " a los ";
		/* 73 */ par.add(new Chunk(texto));
		/*     */
		/* 75 */ String fecha = sdf2.format(new Date());
		/* 76 */ par.add(new Chunk(fecha));
		/*     */
		/* 78 */ texto = ".";
		/* 79 */ par.add(new Chunk(texto));
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/* 129 */ texto = "\n\n\n\n";
		/* 130 */ par.add(new Chunk(texto));
		/*     */
		/* 132 */ par.add(new Chunk(funcionario, fuenteBold));
		/*     */
		/* 134 */ texto = "\n";
		/* 135 */ par.add(new Chunk(texto));
		/*     */
		/* 137 */ texto = "Subadministrador de Servicios al Contribuyente de " + nombrealsc;
		/* 138 */ par.add(new Chunk(texto, fuenteBold));
		/*     */
		/* 140 */ pdf.add(par);
		/*     */ }
	/*     */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2019sep05_4.war!\WEB-INF\lib\visor-
 * documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\vf
 * \integration\service\impl\LeyendaSubAdmin.class Java compiler version: 8
 * (52.0) JD-Core Version: 1.0.7
 */