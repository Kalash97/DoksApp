package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.controller.utils.ServletViewUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EnableDocumentsAction implements Action{

	private ServletView sv;
	
	@Override
	public void launch() {
		sv.getReq().setAttribute("documentsEnable", true);
		ServletViewUtility svu = new ServletViewUtility(sv);
		try {
//			sv.getRes().sendRedirect(ConstantsUtility.SITE);

			svu.forwardTo(ConstantsUtility.SITE);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "EnableDocuments";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
