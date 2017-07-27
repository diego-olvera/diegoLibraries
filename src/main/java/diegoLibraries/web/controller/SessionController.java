package diegoLibraries.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class SessionController {  

	private String sessionName;
	private int maxInactiveInterval;
	
	public SessionController(String sessionName,int maxInactiveInterval) {
		setSessionName(sessionName);
		setMaxInactiveInterval(maxInactiveInterval);
	}
	public SessionController(String sessionName) {
		this(sessionName,0);
	}
	public SessionController() {
		this("user");
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String s) {
		this.sessionName = s;
	}	
	public int getMaxInactiveInterval() {
		return maxInactiveInterval;
	}
	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}
	@RequestMapping(value = "service/logInTest", method = RequestMethod.GET)
	public @ResponseBody Object logInTest(HttpServletRequest request) {
		int idUser=1;
		request.getSession().setMaxInactiveInterval(getMaxInactiveInterval());
		request.getSession().setAttribute(getSessionName(),idUser);
		return idUser;
	}
	@RequestMapping(value = "service/logIn", method = RequestMethod.POST)
	public @ResponseBody Object logIn(HttpServletRequest request) {
		int idUser=getUserId(request);
		HttpSession session=request.getSession();
		if(idUser!=-1) {
			session.setMaxInactiveInterval(getMaxInactiveInterval());
			session.setAttribute(getSessionName(),idUser);
		}	
		return idUser;
	}
	public abstract int getUserId(HttpServletRequest request);
	
	@RequestMapping(value = "service/logOut", method = RequestMethod.GET)
	public @ResponseBody Object logOut(HttpServletRequest request) {
		request.getSession().removeAttribute(getSessionName());
		return null;
	}
}
