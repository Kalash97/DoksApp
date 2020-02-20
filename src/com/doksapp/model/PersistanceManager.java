package com.doksapp.model;

import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;

public interface PersistanceManager {

	public Project createProject(Project project);

	public Project updateProject(Project project);

	public void deleteProject(Project project);

	public Person createUser(Person person);

}
