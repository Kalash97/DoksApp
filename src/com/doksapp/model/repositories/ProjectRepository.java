package com.doksapp.model.repositories;

import com.doksapp.model.PersistanceManager;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.entities.Project;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProjectRepository {

	private PersistanceManager pm;

	public Project createProject(Project project) {
		return pm.createProject(project);
	}

	public Project readProject(QuerySpec qs) {
		return null;
	}

	public Project updateProject(Project project) {
		return pm.updateProject(project);
	}

	public void deleteProject(long id) {
		pm.deleteProject(id);
	}

}
