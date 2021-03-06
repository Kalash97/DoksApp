package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.view.ServletView;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AssignUserToDocAction implements Action{

	private ServletView view;
	private PersonRepository repo;
	
	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		long idPerson = Long.parseLong(view.getId());
		long idDoc = Long.parseLong(view.getName());
		repo.assignDocumentToUser(idPerson, idDoc);
		try {
//			view.getRes().sendRedirect(ConstantsUtility.SITE);
			sv.forwardTo(ConstantsUtility.SITE);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "AssignUserToDoc";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}

}
