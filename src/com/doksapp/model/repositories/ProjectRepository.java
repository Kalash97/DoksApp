package com.doksapp.model.repositories;

import com.doksapp.model.PersistanceManager;
import com.doksapp.model.entities.Project;

public class ProjectRepository {

	private PersistanceManager pm;

	public ProjectRepository(PersistanceManager pm) {
		this.pm = pm;
	}

	public Project createProject(Project project) {
		return pm.createProject(project);
	}

}
