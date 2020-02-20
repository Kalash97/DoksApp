package com.doksapp.model;

import javax.persistence.EntityManager;

import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;

public class HibernatePersistanceManager implements PersistanceManager {

	@Override
	public Project createProject(Project project) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(project);
		em.getTransaction().commit();
		em.close();
		return project;
	}

	@Override
	public Project updateProject(Project project) {//!change
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.merge(project);
		em.refresh(project);
		em.getTransaction().commit();
		em.close();
		return project;
	}

	@Override
	public void deleteProject(Project project) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.remove(project);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Person createUser(Person person) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
		em.close();
		return person;
	}

}
