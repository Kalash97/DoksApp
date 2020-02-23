package com.doksapp.controller.actions;

import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateProjectAction implements Action{

	private View view;
	private ProjectRepository repo;
	
	@Override
	public void launch() {//if desc field is set sets name + desc to new desc
		long id = Long.parseLong(view.getId());
		String name = view.getProjectName();
		String desc = view.getProjectDesc();
		
//		if(name != null & desc != null) {
//			repo.updateProjectAll(id, name, desc);
//		}
//		
//		if(name != null) {
//			repo.updateProjectName(id, name);
//		}
//		
//		if(desc != null) {
//			repo.updateProjectDesc(id, desc);
//		}
	}

	@Override
	public String getName() {
		return "UpdateProject";
	}

}
