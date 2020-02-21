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

	public Project updateProjectName(long id, String name) {
		return pm.updateProjectName(id, name);
	}
	
	public Project updateProjectDesc(long id, String desc) {
		return pm.updateProjectDesc(id, desc);	
	}
	
	public Project updateProjectAll(long id, String name, String desc) {
		return pm.updateProjectAll(id, name, desc);
	}

	public void deleteProject(long id) {
		pm.deleteProject(id);
	}

}
