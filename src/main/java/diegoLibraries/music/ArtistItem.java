package diegoLibraries.music;

import diegoLibraries.commons.NameItem;

public interface ArtistItem extends Iterable<AlbumItem>,NameItem{
	public int numOfAlbums(); 
	public int numOfSongs();
	public void addAlbum(AlbumItem a)throws AlbumRepeatedException;
	public boolean removeAlbum(AlbumItem a);
	public AlbumItem getAlbumItem(int p);
}
