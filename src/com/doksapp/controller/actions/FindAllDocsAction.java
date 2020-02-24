package com.doksapp.controller.actions;

import java.util.List;

import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.DocumentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllDocsAction implements Action{

	private DocumentRepository repo;
	
	@Override
	public void launch() {
		List<Persistable> realAllDocs = repo.readAllDocumnets();
		
		System.out.println(realAllDocs.size());
	}

	@Override
	public String getName() {
		return "FindAllDocs";
	}

}
