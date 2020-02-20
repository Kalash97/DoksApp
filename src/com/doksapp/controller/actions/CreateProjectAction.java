package com.doksapp.controller.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.ProjectRepository;

public class CreateProjectAction implements Action {

	private static Logger logger = LogManager.getLogger(CreateProjectAction.class);
	private ProjectRepository repo;

	public CreateProjectAction(ProjectRepository repo) {
		this.repo = repo;
	}

	@Override
	public void launch() {

		Project p = new Project();
		p.setName("Test");
		p.setDescription("DESC1");
		repo.createProject(p);

		logger.info("Project has been created: " + "Name: " + p.getName());
	}

	@Override
	public String getName() {
		return "CreateProject";
	}

}
