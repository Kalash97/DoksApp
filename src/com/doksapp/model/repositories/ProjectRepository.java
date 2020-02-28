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

	// todo liczba pojedyncza
	public Persistable readProjectsById(String id) {
		QuerySpec qs = new QuerySpec(Project.class);
		qs.addToList(new SearchCondition(Project.class, "id", OperationType.EQUALS, id));
		List<Persistable> results = pm.read(qs);
		if (results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public List<Persistable> findProjectsOfUser(String id) {
		QuerySpec qs = new QuerySpec(Project.class);
		qs.addToList(new SearchCondition(Person.class, "id", OperationType.EQUALS, id));
		qs.addToList(new SearchCondition(Project.class, OperationType.MEMBEROF, Person.class, "projects"));
		return pm.read(qs);
	}

	public Project assignDocumentToProject(long idProject, long idDoc) {
		return pm.assignDocumentToProject(idProject, idDoc);
	}
}
