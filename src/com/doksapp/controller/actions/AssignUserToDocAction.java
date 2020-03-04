package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AssignUserToDocAction implements Action{

	private View view;
	private PersonRepository repo;
	
	@Override
	public void launch() {
		long idPerson = Long.parseLong(view.getId());
		long idDoc = Long.parseLong(view.getTarget());
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
