package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Document;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.ServletView;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateDocumentAction implements Action{

	private static Logger logger = LogManager.getLogger(CreateProjectAction.class);
	private DocumentRepository repo;
	private ServletView view;
	
	public CreateDocumentAction(ServletView view, DocumentRepository repo) {
		this.view = view;
		this.repo=repo;
	}

	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		Document d = new Document();
		d.setName(view.getProjectName());
		d.setContent(view.getProjectDesc());
		repo.createDocument(d);
		
		logger.info("Document has been created: " + "Name: " + d.getName());
		try {
			sv.forwardTo(ConstantsUtility.SITE_DOCUMENTS);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "CreateDocument";
	}
	
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
