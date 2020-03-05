package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RemoveDocumentFromProjectAction implements Action{

	private ServletView view;
	private ProjectRepository repo; 
	
	@Override
	public void launch() {
		long idProject = Long.parseLong(view.getId());
		long idDoc = Long.parseLong(view.getTarget());
		
		repo.removeDocumentFromProject(idProject, idDoc);
	}

	@Override
	public String getName() {
		return "RemoveDocumentFromProject";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER});
	}

}
