package diegoLibraries.repository;

public class DataAccessException extends org.springframework.dao.DataAccessException{
	private static final long serialVersionUID = 1L;
	public DataAccessException(String msg) {
		super(msg);
	}

	
}
