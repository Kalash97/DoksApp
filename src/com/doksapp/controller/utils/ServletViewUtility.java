package com.doksapp.controller.utils;

import java.io.IOException;

import javax.servlet.ServletException;

import com.doksapp.model.entities.Person;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServletViewUtility {
	private ServletView view;

	public void attachUserToUI(Person p) {
		view.getReq().setAttribute("username", p.getName()+" "+p.getLastName());
	}
	
	public void forwardTo(String target) throws ServletException, IOException {
//		RequestDispatcher rd = ServletContext.getRequestDispatcher(target);
		view.getReq().getRequestDispatcher(target).forward(view.getReq(), view.getRes());
	}
}
