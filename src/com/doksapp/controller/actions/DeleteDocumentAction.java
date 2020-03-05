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
public class DeleteDocumentAction implements Action {

	private ServletView view;
	private DocumentRepository repo;

	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		long id = Long.parseLong(view.getId());
		repo.deleteDocument(id);
		
		try {
//			view.getRes().sendRedirect(ConstantsUtility.SITE_DOCUMENTS);

			sv.forwardTo(ConstantsUtility.SITE_DOCUMENTS);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "DeleteDocument";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER });
	}
}
