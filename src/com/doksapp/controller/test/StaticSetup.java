package com.doksapp.controller.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.doksapp.model.HibernateConnection;
import com.doksapp.model.entities.Person;

public class StaticSetup {

	public static void main(String[] args) {
		test1();
	}
	
	private static void test1() {
		EntityManager em = HibernateConnection.getManager();
		
											 
		TypedQuery<?> query = em.createQuery("SELECT P FROM Person P WHERE P.login='testLogin' AND P.password='testPassword'", Person.class);

		 List<?> resultList = query.getResultList();
		 
		 for(Object o : resultList) {
			 System.out.println(">"+o);
		 }
		 
	}

}
