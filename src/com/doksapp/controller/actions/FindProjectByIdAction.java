package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.controller.utils.SessionManager;
import com.doksapp.model.OperationType;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.SearchCondition;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.ServletView;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindProjectByIdAction implements Action{
	
	private ServletView view;
	private ProjectRepository repo;
	
	@Override
	public void launch() {
		String id = view.getId();
		Persistable readProjectsById = repo.readProjectsById(id);
		System.out.println(readProjectsById);
		ServletViewUtility sv = new ServletViewUtility(view);
		Project project = (Project) readProjectsById;
		
		view.getReq().setAttribute("Project111","Name: " + project.getName()+" Desc: "+ project.getDescription());
		try {
//			view.getRes().sendRedirect("Site.jsp");
			sv.forwardTo(ConstantsUtility.SITE);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
		
		
		
		
//		SessionManager sm = new SessionManager(view.getReq());
//		HttpSession currentSesion = sm.getCurrentSesion();
//		if(currentSesion!=null) {
//			String test = (String) currentSesion.getAttribute("TEST");
//		System.out.println(test);
//		}
	}

	@Override
	public String getName() {
		return "FindProjectById";
	}
	
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}

}
