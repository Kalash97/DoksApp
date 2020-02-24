package com.doksapp.model.repositories;

import java.util.List;

import com.doksapp.model.OperationType;
import com.doksapp.model.PersistanceManager;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.SearchCondition;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProjectRepository {

	private PersistanceManager pm;

//	public Project updateProjectName(long id, String name) {
//		return pm.updateProjectName(id, name);
//	}
//	
//	public Project updateProjectDesc(long id, String desc) {
//		return pm.updateProjectDesc(id, desc);	
//	}
//	
//	public Project updateProjectAll(long id, String name, String desc) {
//		return pm.updateProjectAll(id, name, desc);
//	}

	
	
	public Project createProject(Project project) {
		return (Project) pm.create(project);
	}
	
	public void deleteProject(long id) {
		pm.delete(id, Project.class);
	}

	public List<Persistable> readAllProjects() {
		QuerySpec qs = new QuerySpec(Project.class);		
		return pm.read(qs);
	}

	public Persistable readProjectsById(String id) {
		QuerySpec qs = new QuerySpec(Project.class);
		qs.addToList(new SearchCondition(Project.class, "id", OperationType.EQUALS, id));
		List<Persistable> results = pm.read(qs);
		
		return results.get(0);
	}
	
}
