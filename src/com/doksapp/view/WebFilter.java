package com.doksapp.view;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.doksapp.controller.utils.SessionManager;
import com.doksapp.model.entities.Person;

public class WebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter is working!");
		HttpServletRequest req = (HttpServletRequest) request;
		SessionManager sm = new SessionManager(req);
		HttpSession currentSesion = sm.getCurrentSesion();
		if (currentSesion != null) {
			System.out.println(currentSesion);
			//Person p = currentSesion.getAttribute("person1234");
		//	System.out.println(p);
			//String a = currentSesion.getAttribute("a");
		//	System.out.println(a);
			
			System.out.println("On filter: "+currentSesion.getAttribute("newUser"));

		}
		chain.doFilter(request, response);
	}

}
