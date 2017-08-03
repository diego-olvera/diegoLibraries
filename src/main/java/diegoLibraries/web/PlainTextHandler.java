package diegoLibraries.web;

import java.util.ArrayList;

public class PlainTextHandler extends CustomHandler {

	public PlainTextHandler(String method, String path,ShortHandler handler) {
		super(method, path, "text/html", handler);
	}

	public PlainTextHandler(String method, String[] paths,ShortHandler handler) {
		super(method, paths, "text/html", handler);
	}

	public PlainTextHandler(String method, ArrayList<String> paths,ShortHandler handler) {
		super(method, paths, "text/html", handler);
	}
	
	

}
