package com.doksapp.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;

import com.doksapp.model.entities.Person;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.view.ServletView;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAction implements Action{

	private ServletView view;
	private PersonRepository repo;

	@Override
	public void launch() {
		String login = view.getLogin();
		String password = view.getPassword();
		Person p = repo.findPersonByLoginAndPassword(login, password);
		if(p!=null) {
			view.getReq().setAttribute("username", p.getName()+" "+p.getLastName());
		}
		try {
			view.getReq().getRequestDispatcher("Site.jsp").forward(view.getReq(), view.getRes());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Login";
	}

}
