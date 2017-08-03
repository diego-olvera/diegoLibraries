package diegoLibraries.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
	String GET="get";
	String POST="post";
	String DELETE="delete";
	String PUT="put";
	public Object respond(HttpServletRequest req,HttpServletResponse res);
	public String getMethod();
	public String getUrl();
	public List<String> getUrls();
}
