package diegoLibraries.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Clase para el manejo de archivos en linea.
 * @author Diego Olvera
 *
 */
public class InternetFiles {
	/**
	 * Downloads a file and store it in the output file argument.
	 * @param directDownloadLink URL.
	 * @param output Output file.
	 * @throws MalformedURLException If the file doesn't exist.
	 * @throws IOException Input ouput exception.
	 */
	public static void downloadFromUrl(String directDownloadLink, File output) 
			throws MalformedURLException, IOException{
		URL website = new URL(directDownloadLink);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(output);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		//output.createNewFile();
	    //Files.copy(new URL(directDownloadLink).openStream(),output.toPath(),StandardCopyOption.REPLACE_EXISTING);
	}
	/**
	 * Downloads a file and store it in the output file argument.
	 * @param directDownloadLink URL.
	 * @param output Output file path.
	 * @throws MalformedURLException If the file doesn't exist.
	 * @throws IOException Input ouput exception.
	 */
	public static void downloadFromUrl(String directDownloadLink, String output) 
			throws MalformedURLException, IOException{
		downloadFromUrl(directDownloadLink, new File(output));
	}
}
