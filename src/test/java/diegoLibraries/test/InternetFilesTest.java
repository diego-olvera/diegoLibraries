package diegoLibraries.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import static diegoLibraries.net.InternetFiles.*;

public class InternetFilesTest {

	@Test
	public void test() {
		String url="https://publicacionexterna.azurewebsites.net/publicaciones/prices";
		File outputFile=new File("prices.xml");
		try {
			downloadFromUrl(url, outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
