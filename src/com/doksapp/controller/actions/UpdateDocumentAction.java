package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateDocumentAction implements Action{

	private View view;
	private DocumentRepository repo;
	
	@Override
	public void launch() {
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
