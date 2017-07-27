package diegoLibraries.music.itunes;

import java.util.ArrayList;
import java.util.Iterator;

import diegoLibraries.commons.Util;
import diegoLibraries.music.AlbumItem;
import diegoLibraries.music.AlbumRepeatedException;
import diegoLibraries.music.ArtistItem;
import diegoLibraries.music.MultimediaItem;
import diegoLibraries.music.MusicLibraryUtilities;

public class Artist extends MultimediaItem implements ArtistItem{
	
	private String pathFolder;
	private ArrayList<Album> albums;

	public Artist(String name, String pathFolder) {
		setName(name);
		setPathFolder(pathFolder);
		albums=new ArrayList<Album>();
	}

	public String getPathFolder() {
		return pathFolder;
	}

	public void setPathFolder(String pathFolder) {
		this.pathFolder = pathFolder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((pathFolder == null) ? 0 : pathFolder.hashCode());
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
		} else if (!name.equals(other.name))
			return false;
		if (pathFolder == null) {
			if (other.pathFolder != null)
				return false;
		} else if (!pathFolder.equals(other.pathFolder))
			return false;
		return true;
	}
	public void addAlbum(Album a)throws AlbumRepeatedException{
		if(albums.contains(a)){
			throw new AlbumRepeatedException(a.getPathFolder());
		}
		else{
			albums.add(a);
		}
	}
	public boolean removeAlbum(Album a){
		return albums.remove(a);
	}
	public Album getAlbum(int p){
		return albums.get(p);
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

	@Override
	public void addAlbum(AlbumItem a) throws AlbumRepeatedException {
		addAlbum((Album)a);	
	}

	@Override
	public boolean removeAlbum(AlbumItem a) {
		return removeAlbum((Album)a);
	}

	@Override
	public AlbumItem getAlbumItem(int p) {
		return getAlbum(p);
	}
	
	

}
