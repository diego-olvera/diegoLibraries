package diegoLibraries.web;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;

import diegoLibraries.file.FileUtil;
import diegoLibraries.interfaces.CloneObject;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Service;
import spark.Spark;

import static diegoLibraries.web.Handler.*;

public class HttpServer extends Thread implements CloneObject<HttpServer>,Iterable<Handler>{
	private int port;
	private String address;
	private File staticFolder;
	private ArrayList<Handler> pathHandlers=new ArrayList<>();
	private HashMap<String,String> headers=new HashMap<>();
	
	public static final int DEFAULT_PORT=80;
	
	public HttpServer(String address) {
		this(DEFAULT_PORT,address);
	}	
	public HttpServer() {
		this(DEFAULT_PORT);
	}
	public HttpServer(int port) {
		this(port,"0.0.0.0");
	}
	public HttpServer(int port,String address) {
		setPort(port);
		setAddress(address);
		setHeader("Server","diegoLibrariesServer");
	}
	public HttpServer(HttpServer other) {
		this(other.getPort(),other.getAddress());
		for(Handler handler:other) {
			addPathHandler(handler);
		}
		for(String key:headers.keySet()) {
			setHeader(key,headers.get(key));
		}
		setStaticFolder(other.getStaticFolder());
	}
	public HttpServer clone() {
		return new HttpServer(this);	
	}
	public String getAddress() {
		return address;
	}
	
	public void setHeader(String key,String value) {
		headers.put(key, value);
	}
	protected void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	protected void setPort(int port) {
		this.port = port;
	}
	
	public void setStaticFolder(String folder) {
		setStaticFolder(new File(folder));
	}
	public void setStaticFolder(File folder) {
		staticFolder=folder;
	}
	public File getStaticFolder() {
		return staticFolder;
	}
	public boolean addPathHandler(Handler p) {
		return (pathHandlers.contains(p))?false:pathHandlers.add(p);
	}
	public boolean containsPathHandler(Handler p) {
		return pathHandlers.contains(p);
	}
	public boolean removePathHandler(int index) {
		return pathHandlers.remove(index)!=null;
	}
	public boolean removePathHandler(Handler p) {
		return pathHandlers.remove(p);
	}
	public int pathHandlersSize() {
		return pathHandlers.size();
	}
	public Handler getPathHandler(int index) {
		return index>=0&&index<pathHandlersSize()?pathHandlers.get(index):null;
	}
	
	@Override
	public Iterator<Handler> iterator() {
		return new Iterator<Handler>() {
			int currentIndex;
			int size=pathHandlersSize();
			@Override
			public boolean hasNext() {
				return currentIndex<size;
			}

			@Override
			public Handler next() {
				return getPathHandler(currentIndex++);
			}
		};
	}
	public Iterator<Handler> iteratorPathHandlers(){
		return iterator();
	}
	public Iterable<Handler> iterablePathHandler(){
		return this;
		//or
		/*
		 	return new Iterable<PathHandler>() {
			
			@Override
			public Iterator<PathHandler> iterator() {
				return iteratorPathHandlers();
			}
		};
		*/
	}
	public void run() {
		File folder=getStaticFolder();
		Service service=Service.ignite().ipAddress(getAddress()).port(getPort());
		if(folder!=null) {
			service.externalStaticFileLocation(getStaticFolder().toString());
		}
		for(Handler handler:this) {
			switch(handler.getMethod().toLowerCase()) {				
				case POST:
					for(String s:handler.getUrls()) {
						service.post(s,new Route() {		
							public Object handle(Request req, Response res) throws Exception {
								HttpServletResponse rawRes;
								rawRes=res.raw();
								for(String key:headers.keySet()) {
									rawRes.setHeader(key,headers.get(key));
								}
								//handler.setSession(req.session().raw());
								return handler.respond(req.raw(),rawRes);
							}
						});
					}
					break;
				case PUT:
					for(String s:handler.getUrls()) {
						service.put(s,new Route() {		
							public Object handle(Request req, Response res) throws Exception {
								HttpServletResponse rawRes;
								rawRes=res.raw();
								for(String key:headers.keySet()) {
									rawRes.setHeader(key,headers.get(key));
								}
								return handler.respond(req.raw(),rawRes);
							}
						});
					}
					break;
				case DELETE:
					for(String s:handler.getUrls()) {
						service.delete(s,new Route() {		
							public Object handle(Request req, Response res) throws Exception {
								HttpServletResponse rawRes;
								rawRes=res.raw();
								for(String key:headers.keySet()) {
									rawRes.setHeader(key,headers.get(key));
								}
								return handler.respond(req.raw(),rawRes);
							}
						});
					}
					break;
				case GET:default:
					for(String s:handler.getUrls()) {
						service.get(s,new Route() {		
							public Object handle(Request req, Response res) throws Exception {
								HttpServletResponse rawRes;
								rawRes=res.raw();
								for(String key:headers.keySet()) {
									rawRes.setHeader(key,headers.get(key));
								}
								//handler.setSession(req.session().raw());
								//handler.setSession(req.session());
								//req.session().attribute("id",1);
								return handler.respond(req.raw(),rawRes);
							}
						});
					}
					
			}
		}
	}
	public static void standAloneTest() {
		BasicConfigurator.configure();
		Spark.externalStaticFileLocation((System.getProperty("user.dir")));
		Spark.port(80);
		Spark.get("/index.html",new Route() {		
			public Object handle(Request req, Response res) throws Exception {
				StringWriter writer=new StringWriter();
				writer.append("Hello, World");
				req.queryParams();
				writer.append(",h="+req.raw().getParameter("h"));
				res.raw().setContentType("text/json");
				//res.raw().getWriter().append("Hola");
				//System.out.println(res.raw().getWriter().toString());
				return writer;
			}
		});
	}
	public static void main(String[] args) {
		HttpServer server=new HttpServer(80);
		HttpServer otherServer;
		
		Handler pathHandler=new Handler() {		
			@Override
			public Object respond(HttpServletRequest req,HttpServletResponse res) {
				StringBuilder info=new StringBuilder();
				res.setContentType("application/xml");
				res.setCharacterEncoding("UTF-8");
				HttpSession session=req.getSession();
				try {
					FileUtil.getContents("pom.xml",Charset.forName("utf-8"));
					info.append("<xml>");
					Object obj=session.getAttribute("id");
					Integer id=obj!=null?(Integer)obj:null ;
					if(id==null) {
						info.append("<id>").append(id).append("</id>");
						session.setAttribute("id",1);
					}
					else {
						id++;
						info.append("<id>").append(session.getAttribute("id").toString()).append("</id>");
						session.setAttribute("id",id);
					}
					info.append("<p>").append(req.getParameter("p")).append("</p>");
					info.append("<type>arbol</type>");
					info.append("</xml>");
					System.out.println(info);
					return info.toString();

				} catch (Exception e) {
					return null;
				}
			}		
			@Override
			public String getUrl() {
				return "/index";
			}			
			@Override
			public String getMethod() {
				return GET;
			}
			@Override
			public List<String> getUrls() {
				ArrayList<String> urls=new ArrayList<>();
				urls.add("/index.html");
				urls.add("/index.jsp");
				urls.add("/index.php");
				urls.add("/index");
				urls.add("/");
				return urls;
			}
		};
		server.addPathHandler(pathHandler);
		server.addPathHandler(
				new CustomHandler("get","/holi","text/plain",
						(req)->{
							return "Winter is coming á";
						}
				));
		server.addPathHandler(
				new CustomHandler("get","/exit","text/plain",
						(req)->{
							req.getSession().invalidate();
							req.getSession().removeAttribute("id");
							return "1";
						}
				));
		System.out.println("Initializing http server in "+server.getAddress()+":"+server.getPort());
		server.start();
		otherServer=server.clone();
		otherServer.setPort(81);
		otherServer.setHeader("Server","diegoLibrariesServer2");
		//System.out.println("Initializing http server in "+otherServer.getAddress()+":"+otherServer.getPort());
		//otherServer.start();
		
	}
	
}
