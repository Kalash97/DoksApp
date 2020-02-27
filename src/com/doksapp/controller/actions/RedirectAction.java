package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.doksapp.controller.utils.ConstantsUtility;
import com.doksapp.model.entities.AccountType;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RedirectAction implements Action {
	private ServletView view;
	
	@Override
	public void launch() {
		String target = view.getTarget();
		try {
			if (target != null) {
				view.getRes().sendRedirect(target);
			} else {
				view.getRes().sendRedirect(ConstantsUtility.ERROR_PAGE);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Redirect";
	}

	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}
	
}
