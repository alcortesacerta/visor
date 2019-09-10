/*    */ package mx.com.iecisa.commons.utils;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.File;
/*    */ import java.io.InputStream;
/*    */ import java.io.StringReader;
/*    */ import java.nio.file.Files;
/*    */ import javax.xml.bind.JAXBContext;
/*    */ import javax.xml.bind.Marshaller;
/*    */ import javax.xml.bind.Unmarshaller;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.dom.DOMSource;
/*    */ import javax.xml.transform.stream.StreamSource;
/*    */ import javax.xml.validation.Schema;
/*    */ import javax.xml.validation.SchemaFactory;
/*    */ import javax.xml.validation.Validator;
/*    */ import org.w3c.dom.Document;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XmlFormatter
/*    */ {
/*    */   public static <T> String objectToXml(T object) {
/* 33 */     String result = null;
/*    */     
/*    */     try {
/* 36 */       JAXBContext context = JAXBContext.newInstance(new Class[] { object.getClass() });
/*    */       
/* 38 */       Marshaller marshaller = context.createMarshaller();
/* 39 */       marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/*    */       
/* 41 */       File f = File.createTempFile("file", "");
/*    */       
/* 43 */       marshaller.marshal(object, f);
/* 44 */       byte[] salida = Files.readAllBytes(f.toPath());
/*    */       
/* 46 */       result = new String(salida);
/* 47 */     } catch (Exception e) {
/* 48 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 51 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T> T xmlToObject(Class<T> clazz, String xml) {
/* 56 */     T result = null;
/*    */     
/*    */     try {
/* 59 */       JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { clazz });
/* 60 */       Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
/*    */       
/* 62 */       StringReader reader = new StringReader(xml);
/* 63 */       result = (T)unmarshaller.unmarshal(reader);
/* 64 */     } catch (Exception e) {
/* 65 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 68 */     return result;
/*    */   }
/*    */   
/*    */   public static void validateXmlWithXsd(String xml, InputStream schemaStream) throws Exception {
/* 72 */     DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
/* 73 */     builderFactory.setNamespaceAware(true);
/*    */     
/* 75 */     DocumentBuilder parser = builderFactory.newDocumentBuilder();
/*    */ 
/*    */     
/* 78 */     Document document = parser.parse(new ByteArrayInputStream(xml.getBytes()));
/*    */     
/* 80 */     SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
/*    */ 
/*    */     
/* 83 */     Source schemaFile = new StreamSource(schemaStream);
/* 84 */     Schema schema = factory.newSchema(schemaFile);
/*    */     
/* 86 */     Validator validator = schema.newValidator();
/* 87 */     validator.validate(new DOMSource(document));
/*    */   }
/*    */ }


/* Location:              C:\Users\Alejandro Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\iecisa-commons-0.0.1-20170117.085307-900.jar!\mx\com\iecisa\common\\utils\XmlFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */