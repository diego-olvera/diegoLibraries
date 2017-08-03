package diegoLibraries.web;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface ShortHandler {
	Object respond(HttpServletRequest req);
}
