/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */ import java.util.Arrays;

/*    */
/*    */ public class ArchivoDTO
/*    */ {
	/*    */ private String id;
	/*    */ private String name;
	/*    */ private String description;
	/*    */ private byte[] data;
	/* 11 */ private String dataContentType = "image/jpeg";
	/*    */
	/*    */ private byte[] thumbnail;
	/*    */ private String thumbnailContentType;

	/*    */
	/* 16 */ public String getId() {
		return this.id;
	}

	/*    */
	/*    */
	/* 19 */ public void setId(String id) {
		this.id = id;
	}

	/*    */
	/*    */
	/* 22 */ public String getName() {
		return this.name;
	}

	/*    */
	/*    */
	/* 25 */ public void setName(String name) {
		this.name = name;
	}

	/*    */
	/*    */
	/* 28 */ public String getDescription() {
		return this.description;
	}

	/*    */
	/*    */
	/* 31 */ public void setDescription(String description) {
		this.description = description;
	}

	/*    */
	/*    */
	/* 34 */ public byte[] getData() {
		return this.data;
	}

	/*    */
	/*    */
	/* 37 */ public void setData(byte[] data) {
		this.data = data;
	}

	/*    */
	/*    */
	/* 40 */ public String getDataContentType() {
		return this.dataContentType;
	}

	/*    */
	/*    */
	/* 43 */ public void setDataContentType(String dataContentType) {
		this.dataContentType = dataContentType;
	}

	/*    */
	/*    */
	/* 46 */ public byte[] getThumbnail() {
		return this.thumbnail;
	}

	/*    */
	/*    */
	/* 49 */ public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	/*    */
	/*    */
	/* 52 */ public String getThumbnailContentType() {
		return this.thumbnailContentType;
	}

	/*    */
	/*    */
	/* 55 */ public void setThumbnailContentType(String thumbnailContentType) {
		this.thumbnailContentType = thumbnailContentType;
	}

	/*    */
	/*    */
	/*    */
	/*    */ public String toString() {
		/* 60 */ StringBuilder builder = new StringBuilder();
		/* 61 */ builder.append("ArchivoDTO [");
		/* 62 */ if (this.id != null) {
			/* 63 */ builder.append("id=");
			/* 64 */ builder.append(this.id);
			/* 65 */ builder.append(", ");
			/*    */ }
		/* 67 */ if (this.name != null) {
			/* 68 */ builder.append("name=");
			/* 69 */ builder.append(this.name);
			/* 70 */ builder.append(", ");
			/*    */ }
		/* 72 */ if (this.description != null) {
			/* 73 */ builder.append("description=");
			/* 74 */ builder.append(this.description);
			/* 75 */ builder.append(", ");
			/*    */ }
		/* 77 */ if (this.data != null) {
			/* 78 */ builder.append("data=");
			/* 79 */ builder.append(Arrays.toString(this.data));
			/* 80 */ builder.append(", ");
			/*    */ }
		/* 82 */ if (this.dataContentType != null) {
			/* 83 */ builder.append("dataContentType=");
			/* 84 */ builder.append(this.dataContentType);
			/* 85 */ builder.append(", ");
			/*    */ }
		/* 87 */ if (this.thumbnail != null) {
			/* 88 */ builder.append("thumbnail=");
			/* 89 */ builder.append(Arrays.toString(this.thumbnail));
			/* 90 */ builder.append(", ");
			/*    */ }
		/* 92 */ if (this.thumbnailContentType != null) {
			/* 93 */ builder.append("thumbnailContentType=");
			/* 94 */ builder.append(this.thumbnailContentType);
			/*    */ }
		/* 96 */ builder.append("]");
		/* 97 */ return builder.toString();
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\ArchivoDTO.class Java compiler version: 8
 * (52.0) JD-Core Version: 1.0.7
 */