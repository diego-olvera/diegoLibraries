package diegoLibraries.typeConverter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.InputSource;

import diegoLibraries.file.FileUtil;
import diegoLibraries.file.MyRandomAccessFile;

/**
 * XML and JSON Converters between them
 * @author Diego Olvera
 *
 */
public class ProtocolConverter {
	
	public static void convertJsonToXml(String jsonFile,String xmlFile,boolean format) throws IOException {
		convertJsonToXml(new File(jsonFile),new File(xmlFile),format);
	}
	public static void convertJsonToXml(String jsonFile,boolean format) throws IOException {
		convertJsonToXml(new File(jsonFile),format);
	}
	public static void convertJsonToXml(File jsonFile,boolean format) throws IOException {
		convertJsonToXml(jsonFile, FileUtil.getFileWithNewExtension(jsonFile, ".xml"),format);
	}
	public static void convertJsonToXml(File jsonFile,File xmlFile,boolean format) throws IOException {
		MyRandomAccessFile output=new MyRandomAccessFile(xmlFile, "rw");
		String result;
		output.setLength(0);
		result=convertJsonToXml(FileUtil.getContents(jsonFile, Charset.forName("utf-8")));
		if(format) {
			result=getFormattedXml(result);
		}
		output.writeCharacters(result);
		output.close();
	}
	
	public static void convertXmlToJson(String xmlFile,boolean format) throws IOException {
		convertXmlToJson(new File(xmlFile), format);
	}
	public static void convertXmlToJson(File xmlFile,boolean format) throws IOException {
		convertXmlToJson(xmlFile,FileUtil.getFileWithNewExtension(xmlFile,".json"),format);
	}
	public static void convertXmlToJson(String xmlFile,String jsonFile,boolean format) throws IOException {
		convertXmlToJson(new File(xmlFile),new File(jsonFile),format);
	}
	public static void convertXmlToJson(File xmlFile,File jsonFile,boolean format) throws IOException {
		MyRandomAccessFile jsonOutput=new MyRandomAccessFile(jsonFile, "rw");
		String json;
		jsonOutput.setLength(0);
		json=convertXmlToJson(FileUtil.getContents(xmlFile, Charset.forName("utf-8")));
		if(format) {
			json=getFormattedJson(json);
		}
		jsonOutput.writeCharacters(json);
		jsonOutput.close();
	}
	public static String getFormattedJson(String json) {
		return getFormattedJson(json,2);
	}
	public static String getFormattedJson(String json,int spaces) {
		return null;
		//return new GsonObject(json).toString(spaces);
	}
	public static String convertXmlToJson(String xml) {
		return XML.toJSONObject(xml).toString();
	}
	
	public static String convertJsonToXml(String json) {
		return XML.toString(new JSONObject(json));
	}
	public static String getFormattedXml(String xml) {
		return getFormattedXml(xml,2);
	}
	public static String getFormattedXml(String xml,int spaces) {
		 try{
	            Transformer serializer= SAXTransformerFactory.newInstance().newTransformer();
	            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	            //serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	            //serializer.setOutputProperty("{http://xml.customer.org/xslt}indent-amount", "2");
	            Source xmlSource=new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	            StreamResult res =  new StreamResult(new ByteArrayOutputStream());            
	            serializer.transform(xmlSource, res);
	            return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
	        }catch(Exception e){
	            return xml;
	        }
	}
	
}
