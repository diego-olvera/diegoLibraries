package diegoLibraries.test;

import static diegoLibraries.typeConverter.ProtocolConverter.*;

import java.io.IOException;

import org.junit.Test;

import diegoLibraries.file.FileUtil;

public class ProtocolConverterTest {

	@Test()
	public void test() {
		try {
			String[] newFiles={"prices2.json","prices2.xml"};
			convertXmlToJson("prices.xml",newFiles[0],true);
			convertJsonToXml(newFiles[0],newFiles[1],true);
			FileUtil.deleteFiles(newFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
