package diegoLibraries.music;

import java.util.ArrayList;

public class Util {
	
	public static ArrayList<String> getDefaultMusicExtensions(){
		ArrayList<String> exts=new ArrayList<>();
		exts.add(".mp3");
		exts.add(".aac");
		exts.add(".m4a");
		return exts;
	}
}	
