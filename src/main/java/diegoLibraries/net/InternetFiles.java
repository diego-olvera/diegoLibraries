package diegoLibraries.net;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Clase para el manejo de archivos en linea.
 * @author Diego Olvera
 *
 */
public class InternetFiles {
	/**
	 * Descarga el archivo de la URL especifica y lo almacena en la ruta especificada.
	 * @param directDownloadLink Link del archivo en linea.
	 * @param downloadFilePath Ruta del archivo destino a guardar.
	 * @throws MalformedURLException En caso de que el link no exista.
	 * @throws IOException Por si no se puede escribir en el archivo destino.
	 */
	public static void downloadFromUrl(String directDownloadLink, String downloadFilePath) 
			throws MalformedURLException, IOException{
	    URL url = new URL(directDownloadLink);
	    File f=new File(downloadFilePath);
	    f.createNewFile();
	    Files.copy(url.openStream(),f.toPath(),StandardCopyOption.REPLACE_EXISTING);
	}
}
