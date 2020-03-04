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
import com.doksapp.model.entities.Person;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindDocumentsOfUserAction implements Action{

	private ServletView view;
	private DocumentRepository repo;
	
	@Override
	public void launch() {
		SessionManager sm = new SessionManager(view.getReq());
		HttpSession currentSesion = sm.getCurrentSesion();
		System.out.println("Session: "+currentSesion);
		ServletViewUtility sv = new ServletViewUtility(view);
		
		
		if (currentSesion==null) {
			System.out.println("View: "+view);
			System.out.println("Response: "+view.getRes());
			
			

//			view.getRes().sendRedirect("ErrorPage.html");
			try {
				sv.forwardTo(ConstantsUtility.ERROR_PAGE);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else {
			Person person = (Person) currentSesion.getAttribute("newUser");
			sv.attachUserToUI(person);
			Long idLong = person.getId();
			String id = idLong.toString();
			System.out.println("FindDocumentsOfUser: "+view.getReq());
			List<Persistable> list = repo.findDocumentsOfUser(id);
			for(Persistable p : list) {
				System.out.println("Dou: "+ p);
			}
			view.getReq().setAttribute("document444", list);
			try {
//				view.getRes().sendRedirect("Site.jsp");
				sv.forwardTo(ConstantsUtility.SITE);
			} catch (IOException | ServletException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getName() {
		return "FindDocumentsOfUser";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}

}
