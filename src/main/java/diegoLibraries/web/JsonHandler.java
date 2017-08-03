package diegoLibraries.web;

import java.util.ArrayList;

public class JsonHandler extends CustomHandler {

	public JsonHandler(String method, String path,ShortHandler handler) {
		super(method, path, "application/json", handler);
	}

	public JsonHandler(String method, String[] paths,ShortHandler handler) {
		super(method, paths, "application/json", handler);
	}

	public JsonHandler(String method, ArrayList<String> paths,ShortHandler handler) {
		super(method, paths, "application/json", handler);
	}
	
	

}
