package diegoLibraries.music;

import java.util.ArrayList;
import java.util.Iterator;

import diegoLibraries.commons.Util;

public class Album extends MultimediaItem implements AlbumItem{
	
	private Artist artist;
	private ArrayList<Song> songs;
	
	public Album(Artist artist, String name) {
		super(name);
		setArtist(artist);
		songs=new ArrayList<Song>();
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((songs == null) ? 0 : songs.hashCode());
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
		Album other = (Album) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (songs == null) {
			if (other.songs != null)
				return false;
		} else if (!songs.equals(other.songs))
			return false;
		return true;
	}
	public void addSong(Song s)throws SongRepeatedException{
		if(songs.contains(s)){
			throw new SongRepeatedException(s.getFile().getAbsolutePath());
		}
		else{
			songs.add(s);
		}
	}
	public void addSong(SongItem s)throws SongRepeatedException{
		addSong((Song)s);
	}
	public boolean removeSong(Song s){
		return songs.remove(s);
	}
	public boolean removeSong(SongItem s){
		return removeSong((Song)s);
	}
	public Song getSong(int p){
		return songs.get(p);
	}
	public SongItem getSongItem(int p) {
		return getSong(p);
	}
	public int numOfsongs(){
		return songs.size();
	}
	@Override
	public Iterator<SongItem> iterator() {
		return new Iterator<SongItem>() {
			Iterator<Song> iterator=songs.iterator();
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}
			@Override
			public SongItem next() {
				return iterator.next();
			}
		};
	}
	public String toString(){
		StringBuilder info=new StringBuilder();
		info.append("Artist's name:"+artist.getName()+Util.JUMP_LINE);
		info.append("Album's name:"+getName()+Util.JUMP_LINE);
		for(SongItem song:this){
			info.append(song);
		}
		return info.toString();
	}
}
