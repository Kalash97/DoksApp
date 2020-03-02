package com.doksapp.model.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.doksapp.model.HibernateConnection;
import com.doksapp.model.OperationType;
import com.doksapp.model.PersistanceManager;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.SearchCondition;
import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DocumentRepository {
	
	private PersistanceManager pm;
	
	public Document updateDocName(long id, String name) {
		return pm.updateDocName(id, name);
	}
	
	public Document updateDocContent(long id, String content) {
		return pm.updateDocDesc(id, content);	
	}
	
	public Document updateDocAll(long id, String name, String content) {
		return pm.updateDocAll(id, name, content);
	}
	
	public Document createDocument(Document document) {
		return (Document) pm.create(document);
		
	}
	
	public void deleteDocument(long id) {
		pm.delete(id, Document.class);
	}
	
	public List<Persistable> readAllDocumnets(){
		QuerySpec qs = new QuerySpec(Document.class);
		return pm.read(qs);
	}
	
	public Persistable readDocumentsById(String id) {
		QuerySpec qs = new QuerySpec(Document.class);
		qs.addToList(new SearchCondition(Document.class, "id", OperationType.EQUALS, id));
		List<Persistable> results = pm.read(qs);
		
		return results.get(0);
	}

	public List<Persistable> findDocumentsOfProject(String id) {
		QuerySpec qs = new QuerySpec(Document.class);
		qs.addToList(new SearchCondition(Project.class, "id", OperationType.EQUALS, id));
		qs.addToList(new SearchCondition(Document.class, OperationType.MEMBEROF, Project.class, "documents"));
		return pm.read(qs);
	}
}
