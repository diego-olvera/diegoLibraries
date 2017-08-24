package diegoLibraries.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.function.Consumer;

public class FileUtil {
	
	public static void deleteFiles(ArrayList<File> files) {
		files.forEach(FileUtil::deleteDirectory);
	}
	public static void deleteFiles(String[] files) {
		for(String f:files) {
			deleteDirectory(f);
		}
	}
	public static boolean delete(String f) {
		return deleteDirectory(f);
	}
	public static boolean delete(File f) {
		return deleteDirectory(f);
	}
	public static File getFileWithNewExtension(File file,String newExt) {
		String newFileExt=FileUtil.getPathWithoutFileName(file.getAbsolutePath());
		newFileExt=newFileExt+File.separator+FileUtil.removeLastExtension(file.getName())+newExt;
		return new File(newFileExt);
	}
	/***
	 * Remove any extensions, like "OlveraGutierrezDiegoJesus.zip.zip.zip"
	 * @param nameFile
	 * @return string with no extensions like "OlveraGutierrezDiegoJesus"
	 */
	public static String removeAnyExtensions(String nameFile){
		try{
			return nameFile.substring(0, nameFile.indexOf("."));
		}catch(IndexOutOfBoundsException e){
			return nameFile;
		}
	}
	public static String removeLastExtension(String nameFile){
		try{
			return nameFile.substring(0, nameFile.lastIndexOf("."));
		}catch(IndexOutOfBoundsException e){
			return nameFile;
		}
	}
	public static ArrayList<String> getParentFolders(String fileName){
		return getParentFolders(fileName,File.separator);
	}
	public static ArrayList<String> getParentFolders(String fileName,String pathSeparator){
		File file=new File(fileName);
		int sizeFolders;
		ArrayList<String> folders=new ArrayList<>();
		//for(String folder:fileName.split("\\"+pathSeparator)) {
		for(String folder:file.getAbsolutePath().split("\\"+File.separator)) {
			folders.add(folder);
		}
		if((sizeFolders=folders.size())>=1){
			folders.remove(sizeFolders-1);
		}
		return folders;
	}
	public static ArrayList<Integer> createFolders(ArrayList<String> folders) {
		ArrayList<Integer> foldersCreated=new ArrayList<>();
		int i=0;
		StringBuilder foldersConcac=new StringBuilder();
		File currentPath;
		for(String folder:folders) {
			foldersConcac.append(folder).append(File.separator);
			currentPath=new File(foldersConcac.toString());
			if(!currentPath.exists()) {
				if(currentPath.mkdir()) {
					foldersCreated.add(i);
				}
				else {
					break;
				}
			}			
			i++;
		}
		return foldersCreated;
	}
	public static ArrayList<File> getFiles(File directory,ArrayList<String> nameExtensions){
		return getFiles(directory,(String[])nameExtensions.toArray(new String[0]));
	}
	public static ArrayList<File> getFiles(File directory,String[] nameExtensions){
		final ArrayList<File> listOfFiles=new ArrayList<File>();
		Consumer<File>fileConsumer=new Consumer<File>() {
			@Override
			public void accept(File file) {
				String fileName=file.getName();
				for(String extension:nameExtensions){
					if(fileName.endsWith(extension)){
						listOfFiles.add(file); 
					}
				}			
			}
		};
		consumeFiles(directory,fileConsumer);
		return listOfFiles;
	}
	public static ArrayList<File> getFiles(String folder){
		return getFiles(new File(folder));
	}
	public static ArrayList<File> getFiles(File folder){
		ArrayList<File> files=new ArrayList<>();
		consumeFiles(folder, (f)->{
				if(f.isFile()) {
					files.add(f);
				}
			}
		);
		return files;
	}
	
	public static void consumeFiles(File dir,Consumer<File> consumer) {
		for (File file : dir.listFiles()) {
			if(file.isDirectory()){
				consumeFiles(file,consumer);
			} 
			else{		 
				consumer.accept(file);		
			}
		}
	}
	public static ArrayList<File> deleteFiles(File directory,ArrayList<String> nameExtensions){
		ArrayList<File> files;
		(files=getFiles(directory, nameExtensions)).forEach(f->f.delete());
		return files;
	}
	public static ArrayList<File> deleteFiles(File directory,final String[] nameExtensions){
		ArrayList<File> files;
		(files=getFiles(directory, nameExtensions)).forEach(f->f.delete());
		return files;
	}
	public static ArrayList<File> deleteFiles(String dir){
		return deleteFiles(new File(dir));
	}
	public static ArrayList<File> deleteFiles(File dir){
		ArrayList<File> files;
		(files=getFiles(dir)).forEach(f->f.delete());
		return files;
	}
	public static boolean deleteDirectory(String directoryPath){
		return deleteDirectory(new File(directoryPath));
	}
	public static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(File f:files) {
	                if(f.isDirectory()) {
	                    deleteDirectory(f);
	                }
	                else {
	                    f.delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}

	public static ArrayList<File> deleteEmptyFolders(File directory){
		ArrayList<File> deletedFolders=getEmptyFolders(directory);
		deletedFolders.forEach(f->deleteDirectory(f));
		return deletedFolders;
	}
	public static ArrayList<File> getEmptyFolders(File directory){
		ArrayList<File> files=new ArrayList<>();
		ArrayList<File> folders=new ArrayList<>();
		getEmptyFolders(directory,files,folders);
		return folders;
	}
	protected static void getEmptyFolders(File directory,ArrayList<File> files,
			ArrayList<File> folders){
		int numOfCurrentFiles;
		int aux;
		for(File f:directory.listFiles()){
			if(f.isDirectory()){
				numOfCurrentFiles=files.size();
				getEmptyFolders(f,files,folders);
				aux=files.size();
				if(aux<=numOfCurrentFiles){
					folders.add(f);
				}
			}
			else{
				files.add(f);
			}
		}
	}
	public static  String getContents(String file,String charset) throws IOException {
		return getContents(new File(file),Charset.forName(charset));
	}
	public static  String getContents(File file,String charset) throws IOException {
		return getContents(file,Charset.forName(charset));
	}
	public static  String getContents(String file,Charset charset) throws IOException {
		return getContents(new File(file),charset);
	}
	public static  String getContents(File file,Charset charset) throws IOException {
		MyRandomAccessFile raf=new MyRandomAccessFile(file,"r");
		String c=raf.readAllAsString(charset);
		raf.close();
		return c;
	}
	public static ArrayList<Byte> getBytesContents(File file,Charset charset) throws IOException {
		MyRandomAccessFile raf=new MyRandomAccessFile(file,"r");
		ArrayList<Byte> bytes=raf.readAll();
		raf.close();
		return bytes;
	}
	public static String getPathWithoutFileName(String path) {
		return getPathWithoutFileName(path, File.separator);
	}
	public static String getPathWithoutFileName(String path,String sep) {
		return path.substring(0,path.lastIndexOf(sep));
	}
	public static void copyFolder(String src,String dest) throws IOException {
		copyFolder(new File(src),new File(dest));
	}
	public static void copyFolderByGuyFromInternet(String src,String dest) throws IOException {
		copyFolderByGuyFromInternet(new File(src),new File(dest));
	}
	public static void copyFolder(File source,File destination) throws IOException {
		//copyFolderByGuyFromInternet(source,destination);
		copyFolderByDiego(source, destination);
	}
	public static void copyFolderByGuyFromInternet(File source,File destination) throws IOException {
		File f=new File(destination.getAbsolutePath()+File.separator+source.getName());
		f.mkdirs();
		innerCopyFolder(source,f);
	}
	protected static void innerCopyFolder(File source,File destination) throws IOException {
		File srcFile;
		File destFile;
		if (source.isDirectory()){
	        if (!destination.exists()){
	            destination.mkdirs();
	        }
	        String files[] = source.list();
	        for (String file : files) {
	            srcFile = new File(source, file);
	            destFile = new File(destination, file);
	            innerCopyFolder(srcFile, destFile);
	        }
	    }
	    else{
	    	overwriteFile(source, destination);
	    }
	}
	public static void copyFolderByDiego(String src,String dest) throws IOException {
		copyFolderByDiego(new File(src),new File(dest));
	}
	public static void copyFolderByDiego(File src,File dest) throws IOException {
		String aux,absPathSrc=src.getAbsolutePath();
		ArrayList<File> files=getFiles(src);
		File parentPathDest=new File(
				dest.getAbsolutePath()+File.separator+src.getName());
		String parentPathDestStr=parentPathDest.getAbsolutePath();
		parentPathDest.mkdir();	
		String newDir;
		int lengthSrcFolder=absPathSrc.length();
		for(File file:files) {
			aux=file.getAbsolutePath();
			aux=parentPathDestStr+aux.substring(lengthSrcFolder);
			newDir=FileUtil.getPathWithoutFileName(aux);
			new File(newDir).mkdirs();
			copyFile(file, new File(newDir));
		}
	}
	
	public static void copyFile(File src,File dest) throws IOException {
		overwriteFile(src, 
				new File(dest.getAbsolutePath()+File.separator+src.getName()));
	}
	public static void overwriteFile(File src,File dest) throws IOException {
		InputStream in = null;
        OutputStream out = null;

        try
        {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }
        }
        catch (Exception e)
        {
            try
            {
                if(in!=null)in.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }

            try
            {
                if(out!=null)out.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }	

	public static void copyFolders(String src,String dst){
		File sourceFolder,destinyFolder;
		sourceFolder=new File(src);
		destinyFolder=new File(dst);
		FolderCopier folderCopier=new FolderCopier(sourceFolder, destinyFolder);
		folderCopier.copyFolders();
	}

	public static void main(String[] args) throws IOException {
		(getFiles(args[0])).forEach(f->{
			System.out.println(f);
			f.delete();
		});
	}

}
