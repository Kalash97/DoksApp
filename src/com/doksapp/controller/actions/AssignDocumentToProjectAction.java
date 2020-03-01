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
public class AssignDocumentToProjectAction implements Action {

	private View view;
	private ProjectRepository repo;

	@Override
	public void launch() {
		long idProject = Long.parseLong(view.getId());
		long idDoc = Long.parseLong(view.getTarget());

		repo.assignDocumentToProject(idProject, idDoc);
	}

	@Override
	public String getName() {
		return "AssignDocumentToProject";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}

}
