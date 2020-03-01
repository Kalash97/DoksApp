package com.doksapp.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ServletView implements View{
	
	@Getter
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
	public String getId() {
		return req.getParameter("id");
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

	@Override
	public String getType() {
		return req.getParameter("type");
	}
}
