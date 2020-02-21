package com.doksapp.controller.actions;

import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProjectAction implements Action{

	private View view;
	private ProjectRepository repo;
	

	@Override
	public void launch() {
		long id = Long.parseLong(view.getIdProject());
		repo.deleteProject(id);
	}

	@Override
	public String getName() {
		return "DeleteProject";
	}

}
