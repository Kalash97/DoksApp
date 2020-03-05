package com.doksapp.model;

import java.util.List;

import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;

public interface PersistanceManager {

	public Project updateProjectName(long id, String name);
	
	public Project updateProjectDesc(long id, String desc);
	
	public Project updateProjectAll(long id, String name, String desc);

	public Persistable create(Persistable persistable);

	public void delete(long id, Class<?> type);

	public List<Persistable> read(QuerySpec qs);

	public Person assignProjectToUser(long idPerson, long idProject);

	public Person assignDocumentToUser(long idPerson, long idDoc);

	public Project assignDocumentToProject(long idProject, long idDoc);

	public Document updateDocName(long id, String name);

	public Document updateDocDesc(long id, String content);

	public Document updateDocAll(long id, String name, String content);

	public void removeDocumentFromProject(long idProject, long idDoc);

	public void removeProjectFromUser(long idPerson, long idProject);

	public void removeDocumentFromUser(long idPerson, long idDoc);

}
