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
public class EnableAdminAction implements Action{

	private ServletView sv;
	
	@Override
	public void launch() {
		sv.getReq().setAttribute("admin", true);
		ServletViewUtility svu = new ServletViewUtility(sv);
		try {
			svu.forwardTo(ConstantsUtility.SITE);
			return;
		} catch (ServletException | IOException |IllegalStateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "EnableAdmin";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[] { AccountType.ADMIN});
	}

}
