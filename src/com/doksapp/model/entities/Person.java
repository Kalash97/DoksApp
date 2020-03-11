package com.doksapp.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person implements Serializable, Persistable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Getter
	@ManyToMany(mappedBy = "persons")
	List<Document> documents = new ArrayList<Document>();

	@Getter
	@ManyToMany(mappedBy = "persons")
	List<Project> projects = new ArrayList<Project>();
}
