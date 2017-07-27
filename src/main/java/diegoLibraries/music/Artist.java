package diegoLibraries.music;

import java.util.ArrayList;
import java.util.Iterator;

import diegoLibraries.commons.Util;

public class Artist extends MultimediaItem implements ArtistItem{
	
	private ArrayList<Album> albums;

	public Artist(String name) {
		super(name);
		albums=new ArrayList<Album>();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Artist other = (Artist) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.trim().equalsIgnoreCase(other.name.trim()))
			return false;
		return true;
	}
	public void addAlbum(Album a)throws AlbumRepeatedException{
		if(albums.contains(a)){
			throw new AlbumRepeatedException(a.getName());
		}
		else{
			albums.add((Album)a);
		}
	}
	public void addAlbum(AlbumItem a)throws AlbumRepeatedException{
		addAlbum((Album)a);
	}
	public boolean removeAlbum(Album a){
		return albums.remove(a);
	}
	public boolean removeAlbum(AlbumItem a) {
		return removeAlbum((Album)a);
	}
	public Album getAlbum(int p){
		return albums.get(p);
	}
	public AlbumItem getAlbumItem(int p){
		return getAlbum(p);
	}
	public ArrayList<Album> getAlbums(String search){
		ArrayList<Album> albums=new ArrayList<>(); 
		for(Album a:albums) {
			if(a.getName().contains(search)) {
				albums.add(a);
			}
		}
		return albums;
	}
	public Album getAlbum(String search){
		search=search.trim();
		for(Album a:albums) {
			if(a.getName().trim().equalsIgnoreCase(search)) {
				return a;
			}
		}
		return null;
	}
	public int numOfSongs(){
		return MusicLibraryUtilities.numOfSongs(this);
	}
	public int numOfAlbums(){
		return albums.size();
	}
	@Override
	public Iterator<AlbumItem> iterator() {
		return new Iterator<AlbumItem>() {
			Iterator<Album> iterator=albums.iterator();
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}
			@Override
			public AlbumItem next() {
				return iterator.next();
			}
		};
	}
	public String toString(){
		StringBuilder info=new StringBuilder();
		info.append("Artist name:"+getName()+Util.JUMP_LINE);
		for(AlbumItem album:this){
			info.append(album);
		}
		return info.toString();
	}
}
