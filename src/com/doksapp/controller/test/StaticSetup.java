package com.doksapp.controller.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.doksapp.model.HibernateConnection;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Project;

public class StaticSetup {

	public static void main(String[] args) {
		test1();
	}
	
	private static void test1() {
		EntityManager em = HibernateConnection.getManager();
		
//											 "SELECT P FROM Project P WHERE P.id=15"
		TypedQuery<?> query = em.createQuery("SELECT P FROM Project P WHERE P.id=15", Project.class);

		 List<?> resultList = query.getResultList();
		 
		 for(Object o : resultList) {
			 System.out.println(">"+o);
		 }
		 
	}

}
