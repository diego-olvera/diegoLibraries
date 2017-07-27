package diegoLibraries.file.compress;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import diegoLibraries.file.FileUtil;


/**
 * Clase para comprimir y descomprimir archivos con extension zip.
 * @author Diego Olvera
 *
 */
public class Zip{
    
    private static final int BUFFER_SIZE = 4096;
     
    /**
     * Unzip a file
     * @param zipFilePath Zip File Path to unzip.
     * @return the destiny directory of the unzip file.
     * @throws IOException IOException.
     */
    public static String unzip(String zipFilePath) throws IOException{
    	String destDirectory;
    	unzip(zipFilePath,destDirectory=FileUtil.removeLastExtension(zipFilePath));
    	return destDirectory;
    }
    /**
     * Unzip a file
     * @param zipFilePath Zip File Path to unzip.
     * @param destDirectory Destiny directory.
     * @throws IOException IOException.
     */
    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (entry.isDirectory()) {
            	// if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();    
            } else {
            	// if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
    
    /**
     * Extracts a zip entry (file entry)
     * @param zipIn ZipInput
     * @param filePath FilePath
     * @throws IOException IOException 
     */
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    
    /**
     * Zip srcFolder
     * @param srcFolder Source Folder
     * @throws IOException IOException
     */
    static public void zip(String srcFolder) throws IOException{
    	int lastIndex=srcFolder.length()-1;
    	zip(srcFolder,(srcFolder.charAt(lastIndex)==File.separatorChar)?
    			srcFolder.substring(0,lastIndex)+".zip"
    			:srcFolder+".zip");
    }
    /**
     * Zip a folder
     * @param srcFolder Source Folder
     * @param destZipFile Destiny Zip File
     * @throws IOException IOException
     */
    static public void zip(String srcFolder, String destZipFile) throws IOException {
        ZipOutputStream zip = null;
        FileOutputStream fileWriter = null;

        fileWriter = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileWriter);

        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
    }
    
    /**
     * Add a file to the zip.
     * @param path Path
     * @param srcFile Source File-
     * @param zip Zip.
     * @throws IOException IOException.
     */
	static private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
	      throws IOException {
	    File folder = new File(srcFile);
	    if (folder.isDirectory()) {
	    	addFolderToZip(path, srcFile, zip);
	    } 
	    else {
	    	byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + File.separator + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
			in.close();
	    }
	}
	
	/**
	 * Add a folder to zip.
	 * @param path Path.
	 * @param srcFolder Source Folder.
	 * @param zip Zip.
	 * @throws IOException IOException.
	 */
    private static void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
          throws IOException {
    	File folder = new File(srcFolder);
		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + File.separator + fileName, zip);
			} 
		    else{
		    	addFileToZip(path+File.separator+folder.getName(),srcFolder+
		    			  File.separator+fileName,zip);
		    }
		}
    }
}
