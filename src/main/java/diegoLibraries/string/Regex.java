package diegoLibraries.string;

import java.util.regex.Pattern;

public class Regex {
	private String regex;
	
	private Pattern pattern;
	
	public Regex(String regex) {
		setRegex(regex);
		pattern=Pattern.compile(regex);
	}
	public boolean matches(String s) {
		return pattern.matcher(s).matches();
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	@Override
	public String toString() {
		return getRegex();
	}
	
}
