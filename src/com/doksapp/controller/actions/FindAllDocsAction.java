package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.DocumentRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FindAllDocsAction implements Action {

	private ServletView view;
	private DocumentRepository repo;

	@Override
	public void launch() {
		List<Persistable> readAllDocs = repo.readAllDocumnets();
		System.out.println("dokumenty: "+readAllDocs.size());
		view.getReq().setAttribute("document123", readAllDocs);

		try {
			view.getReq().getRequestDispatcher("Site.jsp").forward(view.getReq(), view.getRes());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getName() {
		return "FindAllDocs";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
