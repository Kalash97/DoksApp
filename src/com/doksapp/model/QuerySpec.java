package com.doksapp.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class QuerySpec {

	public QuerySpec(Class<?> c) {
		this.resultType = c;
	}

	@Getter
	private Class<?> resultType;

	@Getter
	private List<SearchCondition> searchConditions = new ArrayList<SearchCondition>();

	public void addToList(SearchCondition sc) {
		searchConditions.add(sc);
	}

}
