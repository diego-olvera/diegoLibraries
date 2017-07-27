package diegoLibraries.stream;

public interface GenericStream {
	
	public String readString()throws InputException;
	public boolean sendString(String s)throws OutputException;
	public  void close();
	public int getWaitOfMiliseconds();
	public void setWaitOfMiliseconds(int m);
}
