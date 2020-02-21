package com.doksapp.model;

import javax.persistence.EntityManager;


import com.doksapp.model.entities.Document;
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
	public Project updateProjectName(long id, String name) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		project.setName(name);
		em.getTransaction().commit();
		em.close();
		return project;
	}
	
	@Override
	public Project updateProjectDesc(long id, String desc) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		project.setDescription(desc);
		em.getTransaction().commit();
		em.close();
		return project;
	}

	@Override
	public Project updateProjectAll(long id, String name, String desc) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		project.setName(name);
		project.setDescription(desc);
		em.getTransaction().commit();
		em.close();
		return project;
	}


	@Override
	public void deleteProject(long id) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		em.remove(project);
		//dobre miejsce na loggera
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

	@Override
	public Document createDocument(Document document) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(document);
		em.getTransaction().commit();
		em.close();
		return document;
	}

	@Override
	public void deleteDocument(long id) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Document document = em.find(Document.class, id);
		em.remove(document);
		//dobre miejsce na loggera
		em.getTransaction().commit();
		em.close();
	}

	
}
