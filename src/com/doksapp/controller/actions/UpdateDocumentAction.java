package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.ServletView;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateDocumentAction implements Action{

	private ServletView view;
	private DocumentRepository repo;
	
	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		long id = Long.parseLong(view.getId());
		String docName = view.getProjectName();
		String docContent = view.getProjectDesc();
		
		if(docName.compareTo("")!=0 & docContent.compareTo("")!=0) {
			repo.updateDocAll(id, docName, docContent);
		}
		
		if(docName.compareTo("")!=0 & docContent.compareTo("")==0) {
			repo.updateDocName(id, docName);
		}
		
		if(docName.compareTo("")==0 & docContent.compareTo("")!=0) {
			repo.updateDocContent(id, docContent);
		}
		
		try {
			sv.forwardTo(ConstantsUtility.SITE_DOCUMENTS);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "UpdateDocument";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}

}
