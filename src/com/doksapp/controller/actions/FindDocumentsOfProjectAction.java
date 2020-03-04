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
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.ServletView;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindDocumentsOfProjectAction implements Action {

	private ServletView view;
	private DocumentRepository repo;

	@Override
	public void launch() {
		SessionManager sm = new SessionManager(view.getReq());
		HttpSession currentSesion = sm.getCurrentSesion();
		System.out.println("Session: " + currentSesion);
		ServletViewUtility sv = new ServletViewUtility(view);

		if (currentSesion == null) {
			System.out.println("View: " + view);
			System.out.println("Response: " + view.getRes());
			try {
				sv.forwardTo(ConstantsUtility.ERROR_PAGE);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {

			List<Persistable> list = repo.findDocumentsOfProject(view.getId());
			for (Persistable p : list) {
				System.out.println("Dop: " + p);
			}
			view.getReq().setAttribute("documentsOfProject123", list);
			try {
				sv.forwardTo(ConstantsUtility.SITE);
			}catch (IOException | ServletException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getName() {
		return "FindDocumentsOfProject";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}

}
