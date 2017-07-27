package diegoLibraries.music.itunes;

import java.io.File;

import diegoLibraries.commons.Util;
import diegoLibraries.file.FileUtil;
import diegoLibraries.music.SongItem;
import diegoLibraries.string.StringUtil;


public class Song implements SongItem{	
	private Album album;
	private String fileName;
	
	
	
	public Song(Album album, String fileName) {
		this.album = album;
		this.fileName = fileName;
	}


	public String getFullPath(){
		return album.getPathFolder()+File.pathSeparator+fileName;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
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
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		return true;
	}
	public String toString(){
		StringBuilder info=new StringBuilder();
		info.append("Song's name:"+getFileName()+Util.JUMP_LINE);
		info.append("Album's name:"+getAlbum().getName()+Util.JUMP_LINE);
		return info.toString();
	}


	@Override
	public String getName() {
		return StringUtil.getCamelCaseStringWithSpaces(
				FileUtil.removeAnyExtensions(getFileName()));
	}


	@Override
	public String getLyrics() {
		return "No lyrics yet";
	}


	@Override
	public File getFile() {
		return new File(new File(getFileName()).getAbsolutePath());
	}
	
}
