package diegoLibraries.music;

import diegoLibraries.commons.NameItem;

public interface AlbumItem extends Iterable<SongItem>, NameItem{
	int numOfsongs();
	public void addSong(SongItem s)throws SongRepeatedException;
	public boolean removeSong(SongItem s);
	public SongItem getSongItem(int p);
}
