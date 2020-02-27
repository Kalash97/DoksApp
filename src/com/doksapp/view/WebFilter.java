package com.doksapp.view;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doksapp.controller.actions.Action;
import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.SessionManager;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Person;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class WebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Filter is working!");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String actionName = req.getParameter("action");
		System.out.println("Action to be executed: " + actionName);
		
		
		
		/////////////////////
		if(actionName == null) {
			chain.doFilter(request, response);
			return;
		}
		

		SessionManager sm = new SessionManager(req);
		HttpSession currentSesion = sm.getCurrentSesion();

		try {
			Class<?> t = Class.forName("com.doksapp.controller.actions." + actionName + "Action");
			Action action = (Action) t.newInstance();
			
			if(action.getAllowedRoles().equals(AccountType.getAllTypes())) {
				chain.doFilter(request, response);
				return;
			}

			if (currentSesion != null) {
				System.out.println("Session: " + currentSesion);
				Object user = currentSesion.getAttribute("newUser");
				System.out.println("User on session: " + user);

				if (user != null) {

					//Class<?> t = Class.forName("com.doksapp.controller.actions." + actionName + "Action");
					//Action action = (Action) t.newInstance();
					if (action.getAllowedRoles().contains(((Person) user).getAccountType())) {
						chain.doFilter(request, response);
						return;
					} else {
						res.sendRedirect(ConstantsUtility.ERROR_PAGE);
						return;
					}
				}
			}

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			res.sendRedirect(ConstantsUtility.ERROR_PAGE);
		}

	}

}
