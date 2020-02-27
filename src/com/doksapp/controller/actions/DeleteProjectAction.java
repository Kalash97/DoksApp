package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DeleteProjectAction implements Action{

	private View view;
	private ProjectRepository repo;
	

	@Override
	public void launch() {
		long id = Long.parseLong(view.getId());
		repo.deleteProject(id);
	}

	@Override
	public String getName() {
		return "DeleteProject";
	}
	
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}

}
