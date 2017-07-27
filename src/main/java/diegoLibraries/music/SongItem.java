package diegoLibraries.music;

import java.io.File;

import diegoLibraries.commons.NameItem;

public interface SongItem extends NameItem{
	String getLyrics();
	File getFile();
}
