package diegoLibraries.exception;

import javax.activity.InvalidActivityException;

public class UtilException {
	
	public static RuntimeException getRunTimeException(String s) {
		throw new RuntimeException(new InvalidActivityException(s)); 
	}
}
