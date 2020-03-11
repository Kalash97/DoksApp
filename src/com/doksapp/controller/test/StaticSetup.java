package com.doksapp.controller.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.doksapp.controller.actions.AssignUserToProjectAction;
import com.doksapp.model.HibernateConnection;
import com.doksapp.model.HibernatePersistanceManager;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.PersonRepository;

public class StaticSetup {

	public static void main(String[] args) {
//		createAdmin();
//		createTestData();
//		createManager();
//		createWorker();
//		createDoc();
		//test();
	}
	
	private static void createTestData() {
		Project project = new Project();
		project.setName("Proj1");
		project.setDescription("Desc1");
		
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(project);
		em.getTransaction().commit();
		em.close();
	}

	private static void createAdmin() {
		Person admin = new Person();
		admin.setLogin("admin");
		admin.setPassword("admin");
		admin.setName("admin");
		admin.setLastName("admin");
		admin.setAccountType(AccountType.ADMIN);
		
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(admin);
		em.getTransaction().commit();
		em.close();
	}
	
	private static void createWorker() {
		Person worker = new Person();
		worker.setLogin("worker");
		worker.setPassword("worker");
		worker.setName("worker");
		worker.setLastName("worker");
		worker.setAccountType(AccountType.WORKER);
		

		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(worker);
		em.getTransaction().commit();
		em.close();
	}
	
	private static void createManager() {
		Person manager = new Person();
		manager.setLogin("manager");
		manager.setPassword("manager");
		manager.setName("manager");
		manager.setLastName("manager");
		manager.setAccountType(AccountType.MANAGER);
		

		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(manager);
		em.getTransaction().commit();
		em.close();
	}
	
	private static void createDoc() {
		Document doc = new Document();
		doc.setName("Doc1");
		doc.setContent("Content1");

		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(doc);
		em.getTransaction().commit();
		em.close();
	}
	
	private static void test() {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Persistable persistable = (Persistable) em.find(Project.class, 19L);
		Person p = em.find(Person.class, 2L);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Projects of user: "+p.getProjects());
		p.getProjects().remove(persistable);
		em.remove(persistable);
		// dobre miejsce na loggera
//		em.flush();
//		em.clear();
		em.getTransaction().commit();
		em.close();
	}

}

//HibernatePersistanceManager hpm = new HibernatePersistanceManager();
//PersonRepository repo = new PersonRepository(hpm);
//									 
//TypedQuery<?> query = em.createQuery("SELECT Pr FROM Project Pr, Person Pe WHERE Pe.id=id AND Pe MEMBER OF Pr.persons", Project.class);
//
// List<?> resultList = query.getResultList();
// 
// for(Object o : resultList) {
//	 System.out.println(">"+o);
// }
////
//new AssignUserToProjectAction(repo);
// 