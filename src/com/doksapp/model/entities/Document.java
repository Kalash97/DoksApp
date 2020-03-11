package com.doksapp.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Document implements Serializable, Persistable {

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
	private String content;
	
	@ManyToMany
	@JoinTable(name="document_person", joinColumns = @JoinColumn(name="id_Document"), inverseJoinColumns = @JoinColumn(name="id_Person"))
	@Getter
	List<Person> persons = new ArrayList<Person>();
	
	@ManyToOne
	@Getter
	@Setter
	private Project project;

}
