package diegoLibraries.web;

import java.util.ArrayList;

public class XmlHandler extends CustomHandler {

	public XmlHandler(String method, String path,ShortHandler handler) {
		super(method, path, "application/xml", handler);
	}

	public XmlHandler(String method, String[] paths,ShortHandler handler) {
		super(method, paths, "application/xml", handler);
	}

	public XmlHandler(String method, ArrayList<String> paths,ShortHandler handler) {
		super(method, paths, "application/xml", handler);
	}
	
	

}
