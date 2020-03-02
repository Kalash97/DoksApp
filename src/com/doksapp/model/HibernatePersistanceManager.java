package com.doksapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;
import com.doksapp.view.Servlet;
import com.mysql.cj.Query;

public class HibernatePersistanceManager implements PersistanceManager {

	private static Logger logger = LogManager.getLogger(HibernatePersistanceManager.class);
	
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
	public Document updateDocName(long id, String name) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();		
		Document document = em.find(Document.class, id);
		document.setName(name);
		em.getTransaction().commit();
		em.close();
		return document;
	}

	@Override
	public Document updateDocDesc(long id, String content) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();		
		Document document = em.find(Document.class, id);
		document.setContent(content);
		em.getTransaction().commit();
		em.close();
		return document;
	}

	@Override
	public Document updateDocAll(long id, String name, String content) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();		
		Document document = em.find(Document.class, id);
		document.setName(name);
		document.setContent(content);
		em.getTransaction().commit();
		em.close();
		return document;
	}

	@Override
	public Persistable create(Persistable persistable) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(persistable);
		em.getTransaction().commit();
		em.close();
		return persistable;
	}

	@Override
	public void delete(long id, Class<?> type) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Persistable persistable = (Persistable) em.find(type, id);
		em.remove(persistable);
		// dobre miejsce na loggera
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persistable> read(QuerySpec qs) {
		EntityManager em = HibernateConnection.getManager();

		String prefix = "SELECT " + qs.getResultType().getSimpleName().substring(0, 2) + " FROM ";
		String tables = "";
		String conditions = "";

		HashSet<String> tablesX = new HashSet<String>();

		tablesX.add(qs.getResultType().getSimpleName());
		for (SearchCondition sc : qs.getSearchConditions()) {
			tablesX.add(sc.getEntityType().getSimpleName());
		}

		Iterator<String> iteratorTablesX = tablesX.iterator();

		while (iteratorTablesX.hasNext()) {
			String s = iteratorTablesX.next();
			tables += s + " " + s.substring(0, 2);

			if (iteratorTablesX.hasNext()) {
				tables += ", ";
			}
		}

		Iterator<SearchCondition> iterator = qs.getSearchConditions().iterator();
		if (qs.getSearchConditions().size() > 0) {
			conditions += " WHERE ";
			while (iterator.hasNext()) {
				SearchCondition next = iterator.next();

				if (next.getType() == OperationType.MEMBEROF) {

					conditions += next.getEntityType().getSimpleName().substring(0, 2);
					conditions +=" " + next.getType().getValue()+" ";
					conditions += next.getEntityTypeSecond().getSimpleName().substring(0, 2)+".";
					conditions += next.getFieldOfSecond();

				} else {

					conditions += next.getEntityType().getSimpleName().substring(0, 2);
					conditions += "." + next.getEntityField();
					conditions += next.getType().getValue();
					conditions += "'" + next.getArgument() + "'";

				}

				if (iterator.hasNext()) {
					conditions += " AND ";
				}
			}
		}

		String hql = prefix + tables + conditions;
		logger.info("Read query to be executed: "+hql);

		TypedQuery<?> query = em.createQuery(hql, qs.getResultType());

		return (List<Persistable>) query.getResultList();
	}

	@Override
	public Person assignProjectToUser(long idPerson, long idProject) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, idPerson);
		Project project = em.find(Project.class, idProject);
		person.getProjects().add(project);
		// System.out.println("Przed commit: "+person.getProjects().size());
		em.getTransaction().commit();
		// System.out.println("Po commit: "+person.getProjects().size());
		em.close();
		// System.out.println("Po close: "+person.getProjects().size());
		return person;
	}

	@Override
	public Person assignDocumentToUser(long idPerson, long idDoc) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, idPerson);
		Document document = em.find(Document.class, idDoc);
		person.getDocuments().add(document);
		em.getTransaction().commit();
		em.close();
		return person;
	}

	@Override
	public Project assignDocumentToProject(long idProject, long idDoc) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, idProject);
		Document document = em.find(Document.class, idDoc);
		project.getDocuments().add(document);
		em.getTransaction().commit();
		em.close();
		return project;
	}

	
}

//Class<?> resultType = qs.getResultType();
//String name = resultType.getSimpleName();
//String tables = name + " " + name.charAt(0);
//String hql = "SELECT " + name.charAt(0) + " FROM " + tables;		
