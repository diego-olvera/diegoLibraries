package diegoLibraries.file;

import java.io.File;
import java.util.ArrayList;


/**
 * This is a class for copying folders from a source to a destiny
 * @author Diego Olvera
 *
 */
public class FolderCopier {
	
	private File sourceFolder;
	private File destinyFolder;
	
	private String sourcePath;
	private String destinyPath;
	
	public FolderCopier(String sourceFolder,String destinyFolder) {
		this(new File(sourceFolder),new File(destinyFolder));
	}
	
	public FolderCopier(File sourceFolder, File destinyFolder) {
		setSourceFolder(sourceFolder);
		setDestinyFolder(destinyFolder);
	}

	public File getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(File sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public File getDestinyFolder() {
		return destinyFolder;
	}

	public void setDestinyFolder(File destinyFolder) {
		this.destinyFolder = destinyFolder;
	}
	
	public void copyFolders(){
		ArrayList<String> nameFolders=new ArrayList<>();
		sourcePath=sourceFolder.getAbsolutePath();
		destinyPath=destinyFolder.getAbsolutePath();
		setNameFolders(sourceFolder, nameFolders);
		for(String path:nameFolders){
			System.out.println("Copying folder "+path);
			new File(path).mkdir();
		}
	}
	private void setNameFolders(File dir,ArrayList<String> nameFolders) {
		String newPath;
		for (File file : dir.listFiles()) {
			if(file.isDirectory()){
				newPath=getNewPath(file.getAbsolutePath());
				nameFolders.add(newPath);
				setNameFolders(file,nameFolders);
			} 
		}
	}
	private String getNewPath(String absolutePath){
		return destinyPath+getRelativePath(absolutePath);
	}
	private String getRelativePath(String path){
		String relativePath;
		int index=path.indexOf(sourcePath)+sourcePath.length();
		relativePath=path.substring(index);
		return relativePath;
	}
	public static String getLastFolder(String completePath){
		int index=completePath.lastIndexOf(File.separator);
		if(index>=0){
			try{
				return completePath.substring(index+1);
			}catch(IndexOutOfBoundsException e){
				return completePath;
			}
		}
		else{
			return completePath;
		}
	}
	public static void main(String[] args) {
		String src,dst;
		src="D:\\Diego Olvera\\Videos\\TV Series";
		dst="C:\\Users\\Diego Olvera\\Videos\\TV Series";
		FileUtil.copyFolders(src, dst);
		//changeSeasonFolders("D:\\Diego Olvera\\Videos\\TV Series");
	}
}
