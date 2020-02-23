package com.doksapp.controller.actions;

import java.util.List;

import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.ProjectRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllProjectsAction implements Action {

	private ProjectRepository repo;

	@Override
	public void launch() {
		List<Persistable> readAllProjects = repo.readAllProjects();
		
		System.out.println(readAllProjects.size());
	}

	@Override
	public String getName() {
		return "FindAllProjects";
	}

}
