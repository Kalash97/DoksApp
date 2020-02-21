package com.doksapp.controller.actions;

import java.io.IOException;

import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RedirectAction implements Action{
	private ServletView view;

	@Override
	public void launch() {
		String target = view.getTarget();
		try {
			view.getRes().sendRedirect(target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Redirect";
	}
	
	
}
