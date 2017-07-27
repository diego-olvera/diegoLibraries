package diegoLibraries.music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.directory.InvalidAttributesException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import diegoLibraries.file.FileUtil;
import diegoLibraries.stream.Printer;

public class MusicLibrary implements MultimediaLibrary{
	public static final Printer defaultPrinter=new Printer() {		
		@Override
		public void println(Object o) {
			System.out.println(o);
			
		}	
		@Override
		public void print(Object o) {
			System.out.print(o);
			
		}
	};
	public static final Printer emptyPrinter=new Printer() {		
		@Override
		public void println(Object o) {			
		}	
		@Override
		public void print(Object o) {			
		}
	};
	
	private ArrayList<Artist> artists;	
	private File folder;
	private Printer printer;
	private ArrayList<String> musicExtensions;
	private ArrayList<Exception> errorLogs;
	
	protected MusicLibrary() {
	}
	
	public MusicLibrary(File folder) {
		this(folder,Util.getDefaultMusicExtensions());
	}
	public MusicLibrary(File folder,Printer printer) {
		this(folder,emptyPrinter,Util.getDefaultMusicExtensions());
	}
	public MusicLibrary(File folder,ArrayList<String> musicExts) {
		this(folder,defaultPrinter,musicExts);
	}
	public MusicLibrary(File folder, Printer printer,ArrayList<String>musicExts) {
		this();
		setFolder(folder);
		setPrinter(printer);
		setMusicExtensions(musicExts);
	}
	
	public ArrayList<String> getMusicExtensions() {
		return musicExtensions;
	}

	public void setMusicExtensions(ArrayList<String> musicExtensions) {
		this.musicExtensions = musicExtensions;
	}

	public File getFolder() {
		return folder;
	}
	public void setFolder(File folder) {
		this.folder = folder;
	}
	public Printer getPrinter() {
		return printer;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
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
	protected boolean setArtists(ArrayList<Artist> a) {
		artists=a;
		return true;
	}
	public boolean loadLibrary() {
		ArrayList<File> songFiles=FileUtil.getFiles(getFolder(),getMusicExtensions());
		ArrayList<Song> songs=new ArrayList<>();
		Song song;
		Artist artist;
		Album album;
		setErrorLogs(new ArrayList<Exception>());
		setArtists(new ArrayList<Artist>());
		printer.println("Loading library from folder '"+folder.getAbsolutePath()+"'");
		printer.println("Music file extensions to load:");
		for(String m:musicExtensions) {
			printer.println(m);
		}
		for(File songFile:songFiles) {
				try {
					song=new Song(songFile);
					songs.add(song);
					printer.println("File:"+songFile.getAbsolutePath());
					printer.println("Adding song '"+song.getName()+"'");
					artist=getArtist(song.getArtistName());
					try {
						addArtist(artist);
					}catch(ArtistRepeatedException e) {
						printer.println(e.getMessage());
					}
					try {
						album=getAlbum(song.getAlbumName(), artist);
						album.addSong(song);
						artist.addAlbum(album);
					}catch(SongRepeatedException | AlbumRepeatedException e) {
						printer.println(e.getMessage());
					}
				} catch (InvalidAttributesException | CannotReadException | IOException | TagException
						| ReadOnlyFileException | InvalidAudioFrameException e1) {
					printer.println("Error:"+e1.getMessage());
					errorLogs.add(e1);
				}			
		}
		return errorLogs.size()==0;
	}
	protected Artist getArtist(String artistName) {
		int index=artists.indexOf(new Artist(artistName));
		return index>=0?artists.get(index):new Artist(artistName);
	}
	protected boolean addArtist(Artist a) throws ArtistRepeatedException {
		boolean added;
		if(artists.contains(a)) {
			added=false;
			throw new ArtistRepeatedException("Artist '"+a.getName()+"' repeated");
		}
		else {
			added=artists.add(a);
			printer.println("Artist '"+a.getName()+"' added?"+added);
		}
		return true;
	}
	protected Album getAlbum(String albumName,Artist artist){
		Album album=artist.getAlbum(albumName);
		if(album==null) {
			printer.println("Creating new album '"+albumName+"' for artist '"+artist.getName()+"'");
			album=new Album(artist, albumName);
		}
		else {
			printer.println("Already in the library the album '"+albumName+"' from artist '"+artist.getName()+"'");
		}
		return album;
	}
	public boolean saveLibrary() {
		return true;
	}
	
	
	public ArrayList<Exception> getErrorLogs() {
		return errorLogs;
	}

	protected void setErrorLogs(ArrayList<Exception> errorLogs) {
		this.errorLogs = errorLogs;
	}
	
	public static void main(String[] args) {
		String folder="C:\\Users\\Diego Olvera\\Music\\iTunes\\iTunes Music\\";
		File fileFolder=new File(folder);
		MusicLibrary musicLibrary=new MusicLibrary(fileFolder,emptyPrinter);
		musicLibrary.loadLibrary();
		
		System.out.println("Artists:");
		for(ArtistItem a:musicLibrary) {
			for(AlbumItem al:a) {
				for(SongItem s:al) {
					System.out.println(s);
				}
			}
			//System.out.println(a);
		}
		System.out.println("Error logs:");
		for(Exception e:musicLibrary.getErrorLogs()) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
