package diegoLibraries.mail;

import javax.mail.MessagingException;

public class SendMailException extends MessagingException{
	private static final long serialVersionUID = 1L;

	public SendMailException(String message) {
		super(message);
	}
	
}
