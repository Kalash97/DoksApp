package com.doksapp.controller.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SessionManager {

	private HttpServletRequest req;

	public HttpSession createSesion() {
		return req.getSession(true);
	}

	public HttpSession getCurrentSesion() {
		return req.getSession(false);
	}

	public void destroyCutrrentSession() {
		HttpSession session = getCurrentSesion();

		if (session != null) {
			session.invalidate();
		}
	}
}
