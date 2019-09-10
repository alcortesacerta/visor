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
/*     */ public class LeyendaCentral/*     */ implements ILeyenda
/*     */ {
	/*     */ public void leyenda(Document pdf, Map<String, Object> vars) throws DocumentException {
		/* 22 */ String nombrealsc = (String) vars.getOrDefault("nombrealsc", "");
		/* 23 */ String fraccionalsc = (String) vars.getOrDefault("fraccionalsc", "");
		/* 24 */ Integer np = (Integer) vars.getOrDefault("np", Integer.valueOf(0));
		/* 25 */ VersionEnrolamientoGeneralDTO versiondto = (VersionEnrolamientoGeneralDTO) vars.get("versiondto");
		/* 26 */ DatosEnrolamientoDTO datosdto = (DatosEnrolamientoDTO) vars.get("datosdto");
		/* 27 */ String ciudad = (String) vars.getOrDefault("ciudad", "");
		/* 28 */ String funcionario = (String) vars.getOrDefault("funcionario", "");
		/* 29 */ String leyenda = (String) vars.getOrDefault("tipoLeyenda", "");
		/*     */
		/* 31 */ String cargo1 = "";
		/*     */
		/*     */
		/*     */
		/*     */
		/*     */
		/* 37 */ if (leyenda.equals("Central")) {
			/* 38 */ cargo1 = "Administrador Central";
			/* 39 */ } else if (leyenda.equals("Coordinador")) {
			/* 40 */ cargo1 = "Coordinador Nacional";
			/*     */ }
		/* 42 */ else if (leyenda.equals("Admin")) {
			/* 43 */ cargo1 = "Administrador";
			/*     */ }
		/*     */
		/* 46 */ SimpleDateFormat sdf = new SimpleDateFormat("dd' de 'MMMM' de 'YYYY", new Locale("es", "MX"));
		/* 47 */ SimpleDateFormat sdf2 = new SimpleDateFormat("dd' días del mes de 'MMMM' del año 'YYYY",
				new Locale("es", "MX"));
		/*     */
		/* 49 */ int fuenteSize = 11;
		/* 50 */ Font fuente = FontFactory.getFont("Noto-Sans", fuenteSize);
		/* 51 */ Font fuenteBold = FontFactory.getFont("Noto-Sans", fuenteSize, 1);
		/*     */
		/*     */
		/*     */
		/* 55 */ Paragraph par = new Paragraph("", fuente);
		/* 56 */ par.setAlignment(3);
		/*     */
		/* 58 */ Chunk chunk = new Chunk(funcionario);
		/* 59 */ par.add(chunk);
		/*     */
		/*     */
		/* 62 */ String texto = ", " + cargo1 + " ";
		/* 63 */ chunk = new Chunk(texto);
		/* 64 */ par.add(chunk);
		/*     */
		/* 66 */ chunk = new Chunk(nombrealsc);
		/* 67 */ par.add(chunk);
		/*     */
		/* 69 */ texto = /* 70 */ ", con fundamento en los artículos 63, cuarto párrafo del Código Fiscal de la Federación; 13, fracción II en relación con el 12, fracción XXIV, y 32 ";
		/*     */
		/* 72 */ chunk = new Chunk(texto);
		/* 73 */ par.add(chunk);
		/*     */
		/* 75 */ chunk = new Chunk(fraccionalsc);
		/* 76 */ par.add(chunk);
		/*     */
		/* 78 */ texto = /* 79 */ " del Reglamento Interior del Servicio de Administración Tributaria vigente, ";
		/*     */
		/* 81 */ chunk = new Chunk(texto);
		/* 82 */ par.add(chunk);
		/*     */
		/* 84 */ texto = "CERTIFICO:";
		/* 85 */ chunk = new Chunk(texto, fuenteBold);
		/* 86 */ par.add(chunk);
		/*     */
		/* 88 */ texto = " Que la presente representación impresa, que consta de " +
		/* 89 */ Math.round((np.intValue() / 2))
				+ " fojas del expediente de firma electrónica, corresponde al documento digital que obra en los " +
				/* 90 */ " sistemas electrónicos de esta unidad administrativa, mismo que se tuvo a la vista. La presente certificación se expide "
				+
				/* 91 */ "en ";
		/* 92 */ chunk = new Chunk(texto);
		/* 93 */ par.add(chunk);
		/*     */
		/* 95 */ chunk = new Chunk(ciudad);
		/* 96 */ par.add(chunk);
		/*     */
		/* 98 */ texto = " a los ";
		/* 99 */ chunk = new Chunk(texto);
		/* 100 */ par.add(chunk);
		/*     */
		/* 102 */ String fecha = sdf2.format(new Date());
		/* 103 */ chunk = new Chunk(fecha);
		/* 104 */ par.add(chunk);
		/*     */
		/*     */
		/* 107 */ texto = ".";
		/* 108 */ chunk = new Chunk(texto);
		/* 109 */ par.add(chunk);
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
		/* 171 */ texto = "\n\n\n\n";
		/* 172 */ chunk = new Chunk(texto);
		/* 173 */ par.add(chunk);
		/* 174 */ chunk = new Chunk(funcionario, fuenteBold);
		/* 175 */ par.add(chunk);
		/* 176 */ texto = "\n";
		/* 177 */ chunk = new Chunk(texto);
		/* 178 */ par.add(chunk);
		/* 179 */ texto = cargo1 + " " + nombrealsc;
		/* 180 */ chunk = new Chunk(texto, fuenteBold);
		/* 181 */ par.add(chunk);
		/*     */
		/* 183 */ pdf.add(par);
		/*     */ }
	/*     */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2019sep05_4.war!\WEB-INF\lib\visor-
 * documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\saie\vf
 * \integration\service\impl\LeyendaCentral.class Java compiler version: 8
 * (52.0) JD-Core Version: 1.0.7
 */