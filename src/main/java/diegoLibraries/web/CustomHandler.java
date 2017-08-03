package diegoLibraries.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomHandler implements Handler,Iterable<String>{
	private String method;
	private ArrayList<String> paths=new ArrayList<>();
	private String responseType;
	private ShortHandler handler;
	private String encoding="UTF-8";
	
	public CustomHandler(String method, String path, String responseType,ShortHandler handler) {
		this.method = method;
		this.paths.add(path);
		this.responseType = responseType;
		this.handler=handler;
	}
	public CustomHandler(String method, String[] paths, String responseType,ShortHandler handler) {
		this.method = method;
		for(String p:paths) {
			this.paths.add(p);
		}
		this.responseType = responseType;
		this.handler=handler;
	}
	public CustomHandler(String method, ArrayList<String> paths, String responseType,ShortHandler handler) {
		this.method = method;
		this.paths = paths;
		this.responseType = responseType;
		this.handler=handler;
	}

	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public ArrayList<String> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<String> paths) {
		this.paths = paths;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public void configureResponse(HttpServletResponse res) {
		res.setContentType(getResponseType());
		res.setCharacterEncoding(getEncoding());
	}
	@Override
	public Object respond(HttpServletRequest req, HttpServletResponse res) {
		configureResponse(res);
		return handler.respond(req);
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getUrl() {
		return getUrls().get(0);
	}

	public boolean addPath(String p) {
		return (paths.contains(p))?false:paths.add(p);
	}
	public boolean containsPath(String p) {
		return paths.contains(p);
	}
	public boolean removePathHandler(int index) {
		return paths.remove(index)!=null;
	}
	public boolean removePathHandler(String p) {
		return paths.remove(p);
	}
	public int pathsSize() {
		return paths.size();
	}
	public String getPathHandler(int index) {
		return index>=0&&index<pathsSize()?paths.get(index):null;
	}
	
	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			int currentIndex;
			int size=pathsSize();
			@Override
			public boolean hasNext() {
				return currentIndex<size;
			}

			@Override
			public String next() {
				return getPathHandler(currentIndex++);
			}
		};
	}
	public Iterator<String> iteratorPathHandlers(){
		return iterator();
	}
	public Iterable<String> iterablePathHandler(){
		return this;
	}
	@Override
	public List<String> getUrls() {
		return paths;
	}
}
