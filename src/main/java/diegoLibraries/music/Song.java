package diegoLibraries.music;

import java.io.File;
import java.io.IOException;

import javax.naming.directory.InvalidAttributesException;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import diegoLibraries.file.FileUtil;
import diegoLibraries.string.StringUtil;

public class Song extends MultimediaItem implements SongItem{
	private File file;
	private Album album;
	private Tag tag;
	private String albumName;
	private String lyrics;
	private String artistName;
		
	public Song(File file) throws InvalidAttributesException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
		super();
		setFile(file);
	}

	@Override
	public void setName(String n) {
		if(n==null || n.length()<1) {
			n=StringUtil.getCamelCaseStringWithSpaces(FileUtil.removeAnyExtensions(file.getName())).trim();
		}
		name = n;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) throws InvalidAttributesException, CannotReadException, IOException, TagException, 
		ReadOnlyFileException, InvalidAudioFrameException{
		if(!file.exists()) {
			throw new InvalidAttributesException("Song file doesnt exist:"+file.getAbsolutePath());
		}
		this.file=file;
		if(setTag(AudioFileIO.read(file).getTag())) {
			reloadAttributesFromFile();
		}
	}
	protected boolean setTag(Tag t) {
		if(t!=null) {
			tag=t;
			return true;
		}
		return false;
	}
	protected Tag getTag() {
		return tag;
	}
	public String getArtistName() {
		return artistName;
	}
	public String getAlbumName() {
		return albumName;
	}
	public String getLyrics() {
		return lyrics;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public void setAlbumName(String a) {
		if(a==null || a.length()<1) {
			a="Unknown Album";
		}
		this.albumName = a;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public void setArtistName(String a) {
		if(a==null || a.length()<1) {
			a="Unknown Artist";
		}
		this.artistName = a;
	}

	public boolean reloadAttributesFromFile() {
		Tag tag=getTag();
		setName(tag.getFirst(FieldKey.TITLE));
		setArtistName(tag.getFirst(FieldKey.ARTIST));
		setLyrics(tag.getFirst(FieldKey.LYRICS));
		setAlbumName(tag.getFirst(FieldKey.ALBUM));
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		String albumName=getAlbumName();
		String artistName=getArtistName();
		String name=getName();
		result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
		result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		String albumName=getAlbumName();
		String artistName=getArtistName();
		String name=getName();
		if (albumName == null) {
			if (other.albumName != null)
				return false;
		} else if (!albumName.equals(other.albumName))
			return false;
		if (artistName == null) {
			if (other.artistName != null)
				return false;
		} else if (!artistName.equals(other.artistName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "name=" + name + ", file=" + file + ", album=" + album + ", albumName=" + albumName
				+ ", artistName=" + artistName;
	}

	public static void main(String[] args) {
		String fileName="C:\\Users\\Diego Olvera\\Music\\iTunes\\\\iTunes Music\\3 Doors Down\\Away From The Sun\\Here Without You.m4a";
		File file=new File(fileName);
		try {
			Song song=new Song(file);
			System.out.println(song);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
