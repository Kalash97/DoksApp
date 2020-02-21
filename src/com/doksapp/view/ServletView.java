package com.doksapp.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ServletView implements View{

	private HttpServletRequest req;
	
	@Getter
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

	@Override
	public String getIdProjectDel() {
		return req.getParameter("idProjectDel");
	}

	@Override
	public String getIdDocument() {
		return req.getParameter("idDocument");
	}

	@Override
	public String getIdProjectUp() {
		return req.getParameter("idProjectUp");
	}

	@Override
	public String getProjectName() {
		return req.getParameter("projectName");
	}

	@Override
	public String getProjectDesc() {
		return req.getParameter("projectDesc");
	}

	@Override
	public String getTarget() {
		return req.getParameter("target");
	}
}
