package diegoLibraries.interfaces;

public interface AttributesObject<T>{
	public boolean setAtr(T element,Object atr,int whichAtr);
	public Object getAtr(T element,int whichAtr); 
	public String getColumnName(int columnOrWhichAtr);
	public int totalAtrs();
}


