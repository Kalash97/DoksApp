package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Document;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.ServletView;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindDocByIdAction implements Action{

	private ServletView view;
	private DocumentRepository repo;
	
	@Override
	public void launch() {
		String id=view.getId();
		Persistable readDocsById = repo.readDocumentsById(id);
		System.out.println(readDocsById);
		ServletViewUtility sv = new ServletViewUtility(view);
		Document doc = (Document) readDocsById;
		
		view.getReq().setAttribute("Documnet123", "Name: "+doc.getName()+" Content: "+ doc.getContent());
		try {
//			view.getRes().sendRedirect(ConstantsUtility.SITE);

			sv.forwardTo(ConstantsUtility.SITE);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "FindDocById";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
