package diegoLibraries.file;

import java.io.File;

public class TvShowsFolders {

	public static void changeSeasonFolders(String srcPath){
		changeSeasonFolders(new File(srcPath));
	}
	public static void changeSeasonFolders(File srcFile){
		String completePath,lastPath;
		int index;
		File newFolder;
		for (File file : srcFile.listFiles()) {
			if(file.isDirectory()){
				changeSeasonFolders(file);
				completePath=file.getAbsolutePath();
				lastPath=FolderCopier.getLastFolder(completePath);
				if(isItSeasonFolder(lastPath)){
					index=completePath.lastIndexOf(File.separator);
					if(index>=0){
						newFolder= new File(completePath.substring(0,index+1)
								+getNewSeasonFolder(lastPath));
						System.out.println(newFolder);
						file.renameTo(newFolder);
					}
				}
			} 
		}
	}
	public static boolean isItSeasonFolder(String folder){
		return folder.matches("[s|S]+(\\d+)");
	}
	public static String getNewSeasonFolder(String seasonFolder){
		if(seasonFolder.matches("[s|S]\\d")){
			return (seasonFolder.charAt(0)=='s'?'s':'S')+"0"+seasonFolder.charAt(1);
		}
		else if(seasonFolder.matches("[s|S]\\d\\d")){
			return seasonFolder;
		}
		else{
			return seasonFolder;
		}
	}

}
