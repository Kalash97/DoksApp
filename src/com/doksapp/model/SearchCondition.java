package com.doksapp.model;

import lombok.Getter;

public class SearchCondition {

	public SearchCondition(Class<?> entityType, String entityField, OperationType type, String argument) {
		this.entityType = entityType;
		this.entityField = entityField;
		this.type = type;
		this.argument = argument;
	}

	public SearchCondition(Class<?> entityType, OperationType type, Class<?> entityTypeSecond, String fieldOfSecond) {
		this.entityType = entityType;
		this.type = type;
		this.entityTypeSecond = entityTypeSecond;
		this.fieldOfSecond = fieldOfSecond;
	}
	
	@Getter
	private Class<?> entityType;
	
	@Getter
	private Class<?> entityTypeSecond;
	
	@Getter
	private String entityField;
	
	@Getter
	private OperationType type;
	
	@Getter
	private String argument;
	
	@Getter
	private String fieldOfSecond;
	
}
