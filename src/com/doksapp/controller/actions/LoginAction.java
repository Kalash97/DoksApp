package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.controller.utils.SessionManager;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Person;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class LoginAction implements Action {

	private ServletView view;
	private PersonRepository repo;

	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		Person p = repo.findPersonByLoginAndPassword(view.getLogin(), view.getPassword());
		
		if (p != null) {
			sv.attachUserToUI(p);

			SessionManager sm = new SessionManager(view.getReq());
			HttpSession session = sm.createSesion();
			session.setAttribute("newUser", p);

			session = null;
			session = sm.getCurrentSesion();
			System.out.println("Sesja na loginAction" + session);
			System.out.println("Na LoginAction: " + session.getAttribute("newUser"));

			testSession();
			try {
				sv.forwardTo(ConstantsUtility.SITE);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				view.getReq().setAttribute("failed", true);
				sv.forwardTo(ConstantsUtility.LOGIN);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void testSession() {
		System.out.println("------------------------------------------------Test Session");
		SessionManager sm = new SessionManager(view.getReq());
		HttpSession currentSesion = sm.getCurrentSesion();
		System.out.println(currentSesion);
		if (currentSesion != null) {
			System.out.println(currentSesion.getAttribute("newUser"));
		}
	}

	@Override
	public String getName() {
		return "Login";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}

}
