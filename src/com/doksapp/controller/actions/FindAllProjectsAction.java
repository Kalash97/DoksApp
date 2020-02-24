package com.doksapp.controller.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.doksapp.model.entities.Persistable;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllProjectsAction implements Action {
	
	private ServletView view;
	private ProjectRepository repo;

	@Override
	public void launch() {
		List<Persistable> readAllProjects = repo.readAllProjects();
		System.out.println(readAllProjects.size());
		view.getReq().setAttribute("projects555", readAllProjects);
		
		try {
			view.getReq().getRequestDispatcher("Site.jsp").forward(view.getReq(), view.getRes());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "FindAllProjects";
	}

}
