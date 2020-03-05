package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RemoveProjectFromUserAction implements Action{

	private ServletView view;
	private PersonRepository repo;
	
	@Override
	public void launch() {
		long idPerson = Long.parseLong(view.getId());
		long idProject = Long.parseLong(view.getTarget());
		
		repo.removeProjectFromUser(idPerson, idProject);
	}

	@Override
	public String getName() {
		return "RemoveProjectFromUser";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER});
	}

}
