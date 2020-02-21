package com.doksapp.model.repositories;

import com.doksapp.model.PersistanceManager;
import com.doksapp.model.entities.Document;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DocumentRepository {
	
	private PersistanceManager pm;
	
	public Document createDocument(Document document) {
		return pm.createDocument(document);
		
	}
	
	public void deleteDocument(long id) {
		pm.deleteDocument(id);
	}
}
