package com.doksapp.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private String login;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	AccountType accountType;
}