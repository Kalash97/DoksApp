package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.doksapp.controller.utils.SessionManager;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindAllProjectsAction implements Action {
	
	private ServletView view;
	private ProjectRepository repo;

	@Override
	public void launch() {
		List<Persistable> readAllProjects = repo.readAllProjects();
		System.out.println(readAllProjects.size());
		view.getReq().setAttribute("projects555", readAllProjects);
		
//		SessionManager sm = new SessionManager(view.getReq());
//		HttpSession session= sm.createSesion();
//		session.setAttribute("TEST", "Test");
//		
//		Person p = new Person();
//		p.setName("122345678543");
//		session.setAttribute("newUser", p);
//
//		session=null;
//		session=sm.getCurrentSesion();
//	    System.out.println("Na FindAllProjectsAction: "+session.getAttribute("TEST"));
//		
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
		return "FindAllProjects";
	}
	
	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
