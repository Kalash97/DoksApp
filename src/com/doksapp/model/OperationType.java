package com.doksapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OperationType {

	EQUALS("="), LOWER("<"), GREATER(">"), MEMBEROF("MEMBER OF");
	
	@Getter
	String value;
	
}
