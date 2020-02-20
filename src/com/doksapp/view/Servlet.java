package com.doksapp.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.doksapp.model.HibernatePersistanceManager;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.ProjectRepository;

@WebServlet("/traffic")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HibernatePersistanceManager hpm = new HibernatePersistanceManager();
	private static Logger logger = LogManager.getLogger(Servlet.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		logger.error("Logger is OK");

		Project project = new Project();
		project.setName("123");
		project.setDescription("Desc");
		new ProjectRepository(hpm).createProject(project);

	}
}
