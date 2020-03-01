package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
public class FindProjectsOfUserAction implements Action {

	private ServletView view;
	private ProjectRepository repo;

	@Override
	public void launch() {
		SessionManager sm = new SessionManager(view.getReq());
		HttpSession currentSesion = sm.getCurrentSesion();
		System.out.println(currentSesion);
		if (currentSesion == null) {
			try {
				view.getRes().sendRedirect("ErrorPage.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Person person = (Person) currentSesion.getAttribute("newUser");
			Long idLong = person.getId();
			String id = idLong.toString();

			List<Persistable> list = repo.findProjectsOfUser(id);
			for (Persistable p : list) {
				System.out.println("Pou: " + p);
			}
			view.getReq().setAttribute("projects444", list);
			try {
				view.getRes().sendRedirect("Site.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getName() {
		return "FindProjectsOfUser";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}

}