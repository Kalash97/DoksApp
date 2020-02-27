package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.PersonRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}

}
