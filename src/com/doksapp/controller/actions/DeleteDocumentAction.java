package com.doksapp.controller.actions;

import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteDocumentAction implements Action{

	private View view;
	private DocumentRepository repo;

	@Override
	public void launch() {
		long id = Long.parseLong(view.getIdDocument());
		repo.deleteDocument(id);
	}

	@Override
	public String getName() {
		return "DeleteDocument";
	}

}
