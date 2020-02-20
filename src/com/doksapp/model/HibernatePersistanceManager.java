package com.doksapp.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
