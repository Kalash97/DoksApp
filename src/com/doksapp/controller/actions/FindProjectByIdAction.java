package com.doksapp.controller.actions;

import com.doksapp.model.OperationType;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.SearchCondition;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindProjectByIdAction implements Action{
	
	private View view;
	private ProjectRepository repo;
	
	@Override
	public void launch() {
		String id = view.getId();
		Persistable readProjectsById = repo.readProjectsById(id);
		System.out.println(readProjectsById);
	}

	@Override
	public String getName() {
		return "FindProjectById";
	}

}
