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
public class RedirectAction implements Action {
	private ServletView view;
	
	@Override
	public void launch() {
		ServletViewUtility sv = new ServletViewUtility(view);
		String target = view.getTarget();
		try {
			if (target != null) {
				//view.getRes().sendRedirect(target);
//				view.getReq().getRequestDispatcher(target).forward(view.getReq(), view.getRes());
				sv.forwardTo(target);
				return;
			} else {
			//	view.getRes().sendRedirect(ConstantsUtility.ERROR_PAGE);
//				view.getReq().getRequestDispatcher(ConstantsUtility.ERROR_PAGE).forward(view.getReq(), view.getRes());
				sv.forwardTo(ConstantsUtility.ERROR_PAGE);
				return;
			}

		} catch (IOException | ServletException e) {
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
