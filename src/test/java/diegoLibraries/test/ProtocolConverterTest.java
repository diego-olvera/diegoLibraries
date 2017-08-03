package diegoLibraries.test;

import static diegoLibraries.typeConverter.ProtocolConverter.fromJsonToXml;
import static diegoLibraries.typeConverter.ProtocolConverter.fromXmlToJson;
import static diegoLibraries.typeConverter.ProtocolConverter.getFormatXml;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;

import diegoLibraries.file.FileUtil;

public class ProtocolConverterTest {

	@Test
	public void test() {
			//String json = fromXmlToJson(getData());
			//System.out.println(getFormatJson(json));
			String data=getData();
			data=fromJsonToXml(fromXmlToJson(data));
			System.out.println(getFormatXml(data));

			//System.out.println(fromJsonToXml(json));
		
	}
	protected  String getData() {
		try {
			return FileUtil.getContents("prices.xml",Charset.forName("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
