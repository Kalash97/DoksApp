package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.ServletView;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectAction implements Action{

	private ServletView view;
	private ProjectRepository repo;
	
	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		long id = Long.parseLong(view.getId());
		String name = view.getProjectName();
		String desc = view.getProjectDesc();
		
		if(name.compareTo("")!=0 & desc.compareTo("")!=0) {
			repo.updateProjectAll(id, name, desc);
		}
		
		if(name.compareTo("")!=0 & desc.compareTo("")==0) {
			repo.updateProjectName(id, name);
		}
		
		if(name.compareTo("")==0 & desc.compareTo("")!=0) {
			repo.updateProjectDesc(id, desc);
		}
		
		try {
//			view.getRes().sendRedirect(ConstantsUtility.SITE);

			sv.forwardTo(ConstantsUtility.SITE_PROJECTS);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "UpdateProject";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
