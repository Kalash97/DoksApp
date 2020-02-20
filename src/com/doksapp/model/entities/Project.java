package com.doksapp.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;

@Entity
public class Project implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idProject;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String description;
	


	
}
