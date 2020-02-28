package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;


import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindDocumentsOfProjectAction implements Action{

	private View view;
	private DocumentRepository repo;
	
	@Override
	public void launch() {
		List<Persistable> list = repo.findDocumentsOfProject(view.getId());
		for(Persistable p : list) {
			System.out.println("Dop: "+p);
		}
	}

	@Override
	public String getName() {
		return "FindDocumentsOfProject";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}

}
