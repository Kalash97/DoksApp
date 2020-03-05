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
public class PersonRepository {

	private PersistanceManager pm;

	public Person createUser(Person person) {	
		return (Person) pm.create(person);
	}
	
//	public Person findUser(String login, String password) {
//		QuerySpec qs = new QuerySpec(Person.class);
////		qs.addToList(new SearchCondition(Person.class, "login", OperationType.EQUALS, login));
////		qs.addToList(new SearchCondition(Person.class, "password", OperationType.EQUALS, password));
////		
//		//Docelowe zapytanie: Select P from Person P where P.login=login AND P.password=password
//		return (Person) pm.read(qs);
//	}
	
	public Person findPersonByLoginAndPassword(String login, String password) {
		QuerySpec qs = new QuerySpec(Person.class);
		qs.addToList(new SearchCondition(Person.class, "login", OperationType.EQUALS, login));
		qs.addToList(new SearchCondition(Person.class, "password", OperationType.EQUALS, password));
		List<Persistable> read = pm.read(qs);
		return read.size()>0?(Person) read.get(0):null;	
	}

	public Person assignDocumentToUser(long idPerson, long idDoc) {
		return pm.assignDocumentToUser(idPerson, idDoc);
		
	}

	public Person assignProjectToUser(long idPerson, long idProject) {
		return pm.assignProjectToUser(idPerson, idProject);
	}

	public void removeProjectFromUser(long idPerson, long idProject) {
		pm.removeProjectFromUser(idPerson, idProject);
	}

	public void removeDocumentFromUser(long idPerson, long idDoc) {
		pm.removeDocumentFromUser(idPerson, idDoc);
	}
}
