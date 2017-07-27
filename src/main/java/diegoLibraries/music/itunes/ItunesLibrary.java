package diegoLibraries.music.itunes;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import diegoLibraries.music.AlbumItem;
import diegoLibraries.music.AlbumRepeatedException;
import diegoLibraries.music.ArtistItem;
import diegoLibraries.music.ArtistRepeatedException;
import diegoLibraries.music.MultimediaLibrary;
import diegoLibraries.music.MusicLibraryUtilities;
import diegoLibraries.music.SongRepeatedException;
import diegoLibraries.stream.Printer;

public class ItunesLibrary implements MultimediaLibrary{
	protected static Printer defaultPrinter=new Printer() {		
		@Override
		public void println(Object o) {
			System.out.println(o);
			
		}	
		@Override
		public void print(Object o) {
			System.out.print(o);
			
		}
	};
	protected static Printer emptyPrinter=new Printer() {		
		@Override
		public void println(Object o) {			
		}	
		@Override
		public void print(Object o) {			
		}
	};
	public static final String musicExtensions[]={"mp3","aac","m4a"};

	private ArrayList<Artist> artists;
	private String initialPath;
	private Printer printer;
	
	public ItunesLibrary(String initialPath,Printer printer) {
		this.initialPath=initialPath;
		this.printer=printer;
	}
	public ItunesLibrary(String initialPath,boolean emptyPrinter){
		this(initialPath,emptyPrinter?ItunesLibrary.emptyPrinter:defaultPrinter);
	}

	public Printer getPrinter() {
		return printer;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	public String getInitialPath() {
		return initialPath;
	}

	public void setInitialPath(String initialPath) {
		this.initialPath = initialPath;
	}
	public void addArtist(Artist a)throws ArtistRepeatedException{
		if(artists.contains(a)){
			throw new ArtistRepeatedException(a.getPathFolder());
		}
		else{
			artists.add(a);
		}
	}
	public boolean removeArtist(Artist s){
		return artists.remove(s);
	}
	public Artist getArtist(int p){
		return artists.get(p);
	}
	public int numOfArtists(){
		return artists.size();
	}
	@Override
	public Iterator<ArtistItem> iterator() {
		return new Iterator<ArtistItem>() {
			Iterator<Artist> iterator=artists.iterator();
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public ArtistItem next() {
				return iterator.next();
			}
			
		};
	}
	
	public void refreshLibrary(){
		String artistName;
		artists=new ArrayList<>();
		for (File folderArtist : new File(initialPath).listFiles()) {
			if(folderArtist.isDirectory()){//Its an artist
				artistName=folderArtist.getName();
				printer.println("Artist found:"+artistName);
				addAlbums(folderArtist,getArtist(folderArtist,artistName));
			} 
		}
	}
	private Artist getArtist(File folderArtist,String artistName) {
		Artist artist;
		for(ArtistItem a:this){
			if(a.getName().equalsIgnoreCase(artistName)){
				printer.println("We already have in the collection the artist "+artistName);
				return (Artist)a;
			}
		}
		printer.println("Its a new artist");
		artist=new Artist(artistName,folderArtist.getAbsolutePath());
		try {
			addArtist(artist);
		} catch (ArtistRepeatedException e) {
			e.printStackTrace();
		}
		return artist;
	}
	private Album getAlbum(File folderAlbum,String albumName,Artist artist) {
		Album album;
		for(AlbumItem a:artist){
			if(a.getName().equalsIgnoreCase(albumName)){
				printer.println("We already have in the collection the album "+albumName
							+ " from artist "+artist.getName());
				return (Album)a;
			}
		}
		printer.println("Its a new album");
		album= new Album(artist,albumName);
		try {
			artist.addAlbum(album);
		} catch (AlbumRepeatedException e) {
			e.printStackTrace();
		}
		return album;
	}
	private void addAlbums(File artistPath,Artist artist){
		String albumName;
		for (File folderAlbum :artistPath.listFiles()) {
			if(folderAlbum.isDirectory()){//Its an album
				albumName=folderAlbum.getName();
				printer.println("Album found:"+albumName);
				addSongs(folderAlbum,getAlbum(folderAlbum, albumName, artist));
			} 
		}
	}

	private void addSongs(File albumPath,Album album) {
		String songName;
		printer.println("Searching songs in album "+album.getName());
		for (File songFile :albumPath.listFiles()) {
			if(songFile.isFile()){
				songName=songFile.getName();
				if(isSong(songName)){
					printer.println("Song found:"+songName);
					addSong(album,songFile);
				}
				else{
					printer.println("This is not a song:"+songName);
				}
				
			} 
		}		
	}
	public static boolean isSong(String songName){
		for(String ext:musicExtensions){
			if(songName.endsWith("."+ext)){
				return true;
			}
		}
		return false;
	}
	protected void addSong(Album album, File songFile) {
		try {
			album.addSong(new Song(album,songFile.getName()));
		} catch (SongRepeatedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String userName=System.getProperty("user.name");
		String initPath="C:\\Users\\"+userName+"\\Music\\iTunes\\iTunes Music";
		String destinationFileName=initPath+File.separator+"itunesManager.xls";
		ItunesLibrary itunesManager=new ItunesLibrary(initPath,true);
		itunesManager.refreshLibrary();
		try {
			MusicLibraryUtilities.toExcel(new File(destinationFileName),itunesManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*LectorDeExcel lector=new LectorDeExcel(new File(destinationFileName));
		for(int fila=0,columna=0,numFilas=lector.dameFilas(columna);fila<numFilas;fila++){
			System.out.println(lector.leer(fila, columna));
		}
		lector.cerrar();*/
	}
	
}
