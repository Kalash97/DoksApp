package com.doksapp.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

	public HttpSession createSesion(HttpServletRequest req) {
		return req.getSession(true);
	}
	
	public HttpSession getCurrentSesion(HttpServletRequest req) {
		return req.getSession(false);
	}
	
	public void destroyCutrrentSession(HttpSession session) {
		session.invalidate();
	}
}
