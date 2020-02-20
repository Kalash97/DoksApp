package com.doksapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SearchCondition {

	@Getter
	private Class<?> entityType;
	
	@Getter
	private String entityField;
	
	@Getter
	private OperationType type;
	
	@Getter
	private String argument;
	
}
