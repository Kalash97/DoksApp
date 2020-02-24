package com.doksapp.model;

import java.util.List;

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

}
