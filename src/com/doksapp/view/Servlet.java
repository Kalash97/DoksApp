package com.doksapp.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.doksapp.controller.actions.Action;
import com.doksapp.controller.actions.CreateProjectAction;
import com.doksapp.controller.actions.RegisterAction;
import com.doksapp.model.HibernatePersistanceManager;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.model.repositories.ProjectRepository;

@WebServlet("/traffic")
public class Servlet extends HttpServlet {

	List<Action> actions = new ArrayList<Action>();

	private static final long serialVersionUID = 1L;

	HibernatePersistanceManager hpm = new HibernatePersistanceManager();
	private static Logger logger = LogManager.getLogger(Servlet.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("Incoming Get Request");

		prepareActions(req, res);

		runAction(req.getParameter("action"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("Incoming Post Request");
		doGet(req, res);
	}

	private void runAction(String name) {
		logger.info("Given action: " + name);

		for (Action a : actions) {
			if (a.getName().equals(name)) {
				a.launch();
				return;
			}
		}
		logger.info("Didn't find action");
	}

	private void prepareActions(HttpServletRequest req, HttpServletResponse res) {

		HibernatePersistanceManager hpm = new HibernatePersistanceManager();
		ProjectRepository pr = new ProjectRepository(hpm);
		CreateProjectAction cpa = new CreateProjectAction(pr);
		actions.add(cpa);
		ServletView sv = new ServletView(req, res);
		PersonRepository pRep = new PersonRepository(hpm);
		RegisterAction ra = new RegisterAction(sv, pRep);
		actions.add(ra);

	}
}
