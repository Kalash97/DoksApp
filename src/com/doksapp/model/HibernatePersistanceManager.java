package com.doksapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
		System.out.println("Persistable: "+persistable);
		em.remove(persistable);
		// dobre miejsce na loggera
//		em.flush();
//		em.clear();
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteSafe(Persistable project) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		//Persistable persistable = (Persistable) em.find(type, id);
		//em.merge(project);
		
		System.out.println(project);
		//em.remove(project); //to by³o
		em.remove(em.merge(project));
		// dobre miejsce na loggera
//		em.flush();
//		em.clear();
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void deleteProject(Project project) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		System.out.println("In HPM: "+project);
		em.remove(project);
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
			if(sc.getEntityTypeSecond()!=null) {
				tablesX.add(sc.getEntityTypeSecond().getSimpleName());
			}
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
					if(next.isNot()) {
						conditions += " NOT ";
					}
					conditions +=" " + next.getType().getValue()+" ";
					conditions += next.getEntityTypeSecond().getSimpleName().substring(0, 2)+".";
					conditions += next.getFieldOfSecond();

				} else {

					conditions += next.getEntityType().getSimpleName().substring(0, 2);
					conditions += "." + next.getEntityField();
					if(next.isNot()) {
						conditions += " NOT ";
					}
					conditions += next.getType().getValue();
					conditions += "'" + next.getArgument() + "'";

				}

				if (iterator.hasNext()) {
					conditions += " AND ";
				}
			}
		}

		String hql = prefix + tables + conditions;
		System.out.println("Read query to be executed: "+hql);
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
		project.getPersons().add(person);
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
		document.getPersons().add(person);
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
		document.setProject(project);
		em.getTransaction().commit();
		em.close();
		return project;
	}

	@Override
	public void removeDocumentFromProject(long idProject, long idDoc) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, idProject);
		for(int i=0; i<project.getDocuments().size(); i++) {
			if(project.getDocuments().get(i).getId()==idDoc) {
				project.getDocuments().remove(i);
			}
		}	
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void removeProjectFromUser(long idPerson, long idProject) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, idPerson);
		for(int i=0; i<person.getProjects().size(); i++) {
			if(person.getProjects().get(i).getId()==idProject) {
				person.getProjects().remove(i);
			}
		}
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void removeDocumentFromUser(long idPerson, long idDoc) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, idPerson);
		for(int i=0; i<person.getDocuments().size(); i++) {
			if(person.getDocuments().get(i).getId()==idDoc) {
				person.getDocuments().remove(i);
			}
		}
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void safeDeleteOfProject(String id, List<Persistable> resultList) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		long idProject = Long.parseLong(id);
	
		List<Person> persons = resultList.stream().map(p->(Person)p).collect(Collectors.toList());
//		for(int i=0; i<persons.size(); i++) {
//			persons.get(i).getProjects().remove(project);
//			
//			
//			System.out.println(Arrays.toString(persons.get(i).getProjects().toArray()));
//		}
		
		for(Person p : persons) {
			for(Project pr : p.getProjects()) {
				if(pr.getId()==idProject) {
					p.getProjects().remove(pr);
				}
				break;
			}
		}
		
		System.out.println(persons);
		
		Project project = em.find(Project.class, idProject);
		
		if(project.getDocuments().size()>0) {	
			for(int j=0; j<project.getDocuments().size(); j++) {
				project.getDocuments().remove(j);
			}
		}
		
		System.out.println(project.getDocuments());
		em.remove(project);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Persistable updateEntity(Persistable p) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.refresh(em.merge(p));
		em.getTransaction().commit();
		em.close();
		return p;
	}

	

	
}

//Class<?> resultType = qs.getResultType();
//String name = resultType.getSimpleName();
//String tables = name + " " + name.charAt(0);
//String hql = "SELECT " + name.charAt(0) + " FROM " + tables;		
