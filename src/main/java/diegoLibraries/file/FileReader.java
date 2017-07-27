package diegoLibraries.file;
   
public interface FileReader { 
	public String read(int row,int field);
	public void close();  
	public int getMaxRows(); 
	public int getMaxColumns();  
}  
