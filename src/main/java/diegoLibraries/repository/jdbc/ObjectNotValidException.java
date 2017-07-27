package diegoLibraries.repository.jdbc;

public class ObjectNotValidException extends Exception{
	private static final long serialVersionUID = 1L;
	public ObjectNotValidException(String msg){
		super(msg);
	}

}
