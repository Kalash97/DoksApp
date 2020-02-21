package com.doksapp.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServletView implements View{

	private HttpServletRequest req; 
	private HttpServletResponse res;
	
	@Override
	public String getLogin() {
		return req.getParameter("Login");
	}

	@Override
	public String getPassword() {
		return req.getParameter("Password");
	}

	@Override
	public String getName() {
		return req.getParameter("Name");
	}

	@Override
	public String getLastName() {
		return req.getParameter("LastName");
	}
}
