package com.doksapp.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Project implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String description;
	
	@OneToMany
	List<Document> documents = new ArrayList<Document>();
	
}
