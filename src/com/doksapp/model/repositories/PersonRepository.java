package com.doksapp.model.repositories;

import com.doksapp.model.PersistanceManager;
import com.doksapp.model.entities.Person;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonRepository {

	private PersistanceManager pm;

	public Person createUser(Person person) {	
		return pm.createUser(person);
	}
	
}
