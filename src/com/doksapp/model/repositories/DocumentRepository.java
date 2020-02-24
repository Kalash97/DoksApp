package com.doksapp.model.repositories;

import java.util.List;

import com.doksapp.model.OperationType;
import com.doksapp.model.PersistanceManager;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.SearchCondition;
import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Persistable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DocumentRepository {
	
	private PersistanceManager pm;
	
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
}
