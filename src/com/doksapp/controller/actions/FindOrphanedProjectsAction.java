package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.ProjectRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindOrphanedProjectsAction implements Action{

	private ProjectRepository repo;
	
	@Override
	public void launch() {
		List<Persistable> result = repo.findOrphanedProjects();
		for(Persistable p : result) {
			System.out.println(((Project) p).getId());
		}
	}

	@Override
	public String getName() {
		return "FindOrphanedProjects";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}

}
