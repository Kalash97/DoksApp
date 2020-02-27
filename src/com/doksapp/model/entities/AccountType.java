package com.doksapp.model.entities;

import java.util.Arrays;
import java.util.List;

public enum AccountType {
	
	ADMIN, MANAGER, WORKER;
		
	public static List<AccountType> getAllTypes() {
		return Arrays.asList(AccountType.values());
	}
}
