package com.doksapp.controller.actions;

import com.doksapp.model.repositories.ProjectRepository;

public class DeleteProjectAction implements Action{

	private ProjectRepository repo;

	@Override
	public void launch() {
		//repo.deleteProject(3);
	}

	@Override
	public String getName() {
		return "DeleteProject";
	}

}
