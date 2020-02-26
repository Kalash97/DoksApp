package com.doksapp.controller.actions;

import com.doksapp.model.repositories.PersonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignUserToDocAction implements Action{

	private PersonRepository repo;
	
	@Override
	public void launch() {
		long idPerson=11;
		long idDoc=16;
		repo.assignDocumentToUser(idPerson, idDoc);
	}

	@Override
	public String getName() {
		return "AssignUserToDoc";
	}

}
