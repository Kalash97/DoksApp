package com.doksapp.controller.actions;

import com.doksapp.model.entities.Person;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterAction implements Action{

	private View view;
	private PersonRepository repo;
	
	@Override
	public void launch() {
		String login = view.getLogin();
		String password = view.getPassword();
		String name = view.getName();
		String lastName = view.getLastName();
		
		Person person = new Person();
		person.setLogin(login);
		person.setPassword(password);
		person.setName(name);
		person.setLastName(lastName);
		
		repo.createUser(person);
	}

	@Override
	public String getName() {
		return "Register";
	}

}
