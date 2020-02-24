package com.doksapp.controller.actions;

import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindDocByIdAction implements Action{

	private View view;
	private DocumentRepository repo;
	
	@Override
	public void launch() {
		String id=view.getId();
		Persistable readDocsById = repo.readDocumentsById(id);
		System.out.println(readDocsById);
	}

	@Override
	public String getName() {
		return "FindDocById";
	}

}
