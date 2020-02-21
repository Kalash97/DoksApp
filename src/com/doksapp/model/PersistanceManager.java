package com.doksapp.model;

import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;

public interface PersistanceManager {

	public Project createProject(Project project);

	public Project updateProject(Project project);

	public void deleteProject(long id);

	public Person createUser(Person person);

	public Document createDocument(Document document);

	public void deleteDocument(long id);

}
