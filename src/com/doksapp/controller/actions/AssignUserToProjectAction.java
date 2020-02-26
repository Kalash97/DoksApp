package com.doksapp.controller.actions;

import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignUserToProjectAction implements Action{
	
	private PersonRepository repo;
	
	@Override
	public void launch() {
		long idPerson = 11;
		long idProject = 15;
		repo.assignProjectToUser(idPerson, idProject);
	}

	@Override
	public String getName() {
		return "AssignUserToProject";
	}

}
