package com.doksapp.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Entity
@Inheritance(strategy=javax.persistence.InheritanceType.SINGLE_TABLE)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idPerson;
	
	@Getter
	@Setter
	private String name;
	private String lastName;
	private String login;
	private String password;

}
