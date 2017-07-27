package diegoLibraries.mail;

public class MailBody {
	
	public static final String PLAIN_TEXT="text/plain";
	public static final String HTML_TEXT="text/html";
	
	private String content;
	private String type;
	
	public MailBody(String content) {
		this(content,PLAIN_TEXT);
	}
	public MailBody(String content, String type) {
		setContent(content);
		setType(type); 
	}
	public String getContent() {
		return content; 
	}
	
	public void setContent(String c) {
		content = c;
	}
	public String getType() {
		return type;
	}

	public void setType(String t) {
		type=t.equals(PLAIN_TEXT)|| t.equals(HTML_TEXT)?t:PLAIN_TEXT;
	}
	
	
}
