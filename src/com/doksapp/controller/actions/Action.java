package com.doksapp.controller.actions;

import java.util.List;

import com.doksapp.model.entities.AccountType;

public interface Action {

	public void launch();
	public String getName();
	public List<AccountType> getAllowedRoles();
	
}
