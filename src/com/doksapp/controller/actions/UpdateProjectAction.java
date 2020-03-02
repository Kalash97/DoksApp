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
public class UpdateProjectAction implements Action{

	private View view;
	private ProjectRepository repo;
	
	@Override
	public void launch() {//if desc field is set sets name + desc to new desc
		long id = Long.parseLong(view.getId());
		String name = view.getProjectName();
		String desc = view.getProjectDesc();
		
		if(name.compareTo("")!=0 & desc.compareTo("")!=0) {
			repo.updateProjectAll(id, name, desc);
		}
		
		if(name.compareTo("")!=0 & desc.compareTo("")==0) {
			repo.updateProjectName(id, name);
		}
		
		if(name.compareTo("")==0 & desc.compareTo("")!=0) {
			repo.updateProjectDesc(id, desc);
		}
	}

	@Override
	public String getName() {
		return "UpdateProject";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
