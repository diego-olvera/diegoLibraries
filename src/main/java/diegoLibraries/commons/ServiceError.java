package diegoLibraries.commons;

public class ServiceError {
	private int code;
	private String message;
	
	public ServiceError(int code, String message) {
		setCode(code);
		setMessage(message);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
