package com.doksapp.controller.actions;

import java.io.IOException;

import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RedirectAction implements Action {
	private static final String ERROR_PAGE = "ErrorPage.html";
	private ServletView view;

	@Override
	public void launch() {
		String target = view.getTarget();
		try {
			if (target != null) {
				view.getRes().sendRedirect(target);
			} else {
				view.getRes().sendRedirect(ERROR_PAGE);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Redirect";
	}

}
