package diegoLibraries.interfaces;

import java.io.RandomAccessFile;

public interface RAF_Object<T>{ 
	public boolean save(RandomAccessFile r);
	public T load(RandomAccessFile r);
	public int size();  
}
