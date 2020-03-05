package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.ServletView;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateProjectAction implements Action {

	private static Logger logger = LogManager.getLogger(CreateProjectAction.class);
	private ProjectRepository repo;
	private ServletView view;

	public CreateProjectAction(ServletView view, ProjectRepository repo) {
		this.view = view;
		this.repo = repo;
	}

	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		Project p = new Project();
		p.setName(view.getProjectName());
		p.setDescription(view.getProjectDesc());
		repo.createProject(p);

		logger.info("Project has been created: " + "Name: " + p.getName());
		
		try {
//			view.getRes().sendRedirect(ConstantsUtility.SITE_PROJECTS);

			sv.forwardTo(ConstantsUtility.SITE_PROJECTS);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "CreateProject";
	}
	
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN});
	}

}
