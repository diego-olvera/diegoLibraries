package diegoLibraries.web;

import java.util.ArrayList;

public class HtmlTextHandler extends CustomHandler {

	public HtmlTextHandler(String method, String path,ShortHandler handler) {
		super(method, path, "text/html", handler);
	}

	public HtmlTextHandler(String method, String[] paths,ShortHandler handler) {
		super(method, paths, "text/html", handler);
	}

	public HtmlTextHandler(String method, ArrayList<String> paths,ShortHandler handler) {
		super(method, paths, "text/html", handler);
	}
	
	

}
