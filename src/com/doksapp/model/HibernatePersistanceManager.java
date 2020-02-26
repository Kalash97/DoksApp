package com.doksapp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;
import com.mysql.cj.Query;

public class HibernatePersistanceManager implements PersistanceManager {

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

		Class<?> resultType = qs.getResultType();
		String name = resultType.getSimpleName();
		String hql = "SELECT " + name.charAt(0) + " FROM " + name + " " + name.charAt(0);

		Iterator<SearchCondition> iterator = qs.getSearchConditions().iterator();
		if (qs.getSearchConditions().size() > 0) {
			hql += " WHERE ";
			while (iterator.hasNext()) {
				SearchCondition next = iterator.next();
				hql += next.getEntityType().getSimpleName().charAt(0);
				hql += "." + next.getEntityField();
				hql += next.getType().getValue();
				hql += "'" + next.getArgument() + "'";
				
				if (iterator.hasNext()) {
					hql += " AND ";
				}
			}
		}

		TypedQuery<?> query = em.createQuery(hql, resultType);

		return (List<Persistable>) query.getResultList();
	}

	@Override
	public Person assignProjectToUser(long idPerson, long idProject) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, idPerson);
		Project project = em.find(Project.class, idProject);
		person.getProjects().add(project);
		//System.out.println("Przed commit: "+person.getProjects().size());
		em.getTransaction().commit();
		//System.out.println("Po commit: "+person.getProjects().size());
		em.close();
		//System.out.println("Po close: "+person.getProjects().size());
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
}
