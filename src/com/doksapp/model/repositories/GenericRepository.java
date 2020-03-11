package com.doksapp.model.repositories;

import com.doksapp.model.PersistanceManager;
import com.doksapp.model.entities.Persistable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class GenericRepository {
	
	protected PersistanceManager pm;
	
	public Persistable updateEntity(Persistable p) {
		pm.updateEntity(p);
		return p;
	}
	
}
