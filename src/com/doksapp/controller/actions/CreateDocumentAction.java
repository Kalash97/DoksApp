package com.doksapp.controller.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Document;
import com.doksapp.model.repositories.DocumentRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateDocumentAction implements Action{

	private static Logger logger = LogManager.getLogger(CreateProjectAction.class);
	private DocumentRepository repo;
	
	public CreateDocumentAction(DocumentRepository repo) {
		this.repo=repo;
	}

	@Override
	public void launch() {
		Document d = new Document();
		d.setName("Doc1");
		d.setContent("RandomContent");
		repo.createDocument(d);
		
		logger.info("Document has been created: " + "Name: " + d.getName());
	}

	@Override
	public String getName() {
		return "CreateDocument";
	}
	
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
