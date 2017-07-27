package diegoLibraries.music;

import java.io.File;

import diegoLibraries.file.excel.ExcelWriter;


public class MusicLibraryUtilities {

	public static int numOfSongs(ArtistItem ar) {
		int songs=0;
		for(AlbumItem a:ar) {
			songs+=a.numOfsongs();
		}
		return songs;
	}
	public static void toExcel(File file,MultimediaLibrary library) throws Exception  {
		file.delete();
		ExcelWriter writer=new ExcelWriter(file,"Artists");
		int row=0,column=0;
		int songCounter;
		writer.write("Artist",row,0);
		writer.write("Number Of Songs",row,1);
		row=1;
		for(ArtistItem artist:library){
			songCounter=0;
			for(AlbumItem album:artist){
				songCounter+=album.numOfsongs();
			}
			createArtistSheet(writer,artist);
			writer.write(artist.getName(), row,column);
			writer.write(songCounter,row,column+1);
			row+=1;
		}
		writer.close();
	}
	private static void createArtistSheet(ExcelWriter writer,ArtistItem artist){
		int row=0,column=0;
		writer.setCurrentSheet(artist.getName());
		String albumName;
		writer.write("Album",row,0);
		writer.write("Song",row,1);
		writer.write("File",row,2);
		writer.write("Lyrics",row,3);
		row++;
		for(AlbumItem album:artist){
			albumName=album.getName();
			for(SongItem song:album){
				writer.write(albumName,row,column);
				writer.write(song.getName(),row,column+1);
				writer.write(song.getFile().getAbsolutePath(),row,column+2);
				writer.write(song.getLyrics().replaceAll("\n","\r\n"),row,column+3);
				row++;
			}
		}
		writer.setCurrentSheet(0);
	}
	public static MusicLibrary getItunesMusicLibrary() {
		return new MusicLibrary(new File(getItunesMusicPath()),MusicLibrary.emptyPrinter);
	}
	public static String getItunesMusicPath() {
		String userName=System.getProperty("user.name");
		String initPath="C:\\Users\\"+userName+"\\Music\\iTunes\\iTunes Music";
		return initPath;
	}
	public static void main(String[] args) {
		MusicLibrary library=getItunesMusicLibrary();
		File fileDest=new File(getItunesMusicPath()+File.separator+"itunesManager.xls");
		library.loadLibrary();
		try {
			toExcel(fileDest, library);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
