package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindProjectsOfUserAction implements Action{

	private View view;
	private ProjectRepository repo;
	
	@Override
	public void launch() {
		List<Persistable> list = repo.findProjectsOfUser(view.getId());
		for(Persistable p : list) {
			System.out.println("Pou: "+p);
		}
	}

	@Override
	public String getName() {
		return "FindProjectsOfUser";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
