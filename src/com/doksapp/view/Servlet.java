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
import com.doksapp.controller.actions.AssignDocumentToProjectAction;
import com.doksapp.controller.actions.AssignUserToDocAction;
import com.doksapp.controller.actions.AssignUserToProjectAction;
import com.doksapp.controller.actions.CreateDocumentAction;
import com.doksapp.controller.actions.CreateProjectAction;
import com.doksapp.controller.actions.DeleteDocumentAction;
import com.doksapp.controller.actions.DeleteProjectAction;
import com.doksapp.controller.actions.FindAllDocsAction;
import com.doksapp.controller.actions.FindAllProjectsAction;
import com.doksapp.controller.actions.FindDocByIdAction;
import com.doksapp.controller.actions.FindDocumentsOfProjectAction;
import com.doksapp.controller.actions.FindProjectByIdAction;
import com.doksapp.controller.actions.FindProjectsOfUserAction;
import com.doksapp.controller.actions.LoginAction;
import com.doksapp.controller.actions.LogoutAction;
import com.doksapp.controller.actions.RedirectAction;
import com.doksapp.controller.actions.RegisterAction;
import com.doksapp.controller.actions.UpdateProjectAction;
import com.doksapp.model.HibernatePersistanceManager;
import com.doksapp.model.QuerySpec;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.DocumentRepository;
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
		
		String abc = "abc1";
		String abc2= "abc2";
		req.setAttribute("randomAtt", abc);
		req.setAttribute("randomAtt2", abc2);
		System.out.println("Servlet: "+req);
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
		DeleteProjectAction dp = new DeleteProjectAction(sv, pr);
		actions.add(dp);
		DocumentRepository dRep = new DocumentRepository(hpm);
		CreateDocumentAction cda = new CreateDocumentAction(dRep);
		actions.add(cda);
		UpdateProjectAction upa = new UpdateProjectAction(sv, pr);
		actions.add(upa);
		RedirectAction redirectA = new RedirectAction(sv);
		actions.add(redirectA);
		DeleteDocumentAction dda = new DeleteDocumentAction(sv, dRep);
		actions.add(dda);
		FindAllProjectsAction fap = new FindAllProjectsAction(sv, pr);
		actions.add(fap);
		FindProjectByIdAction fpbi = new FindProjectByIdAction(sv, pr);
		actions.add(fpbi);
		FindAllDocsAction fad = new FindAllDocsAction(sv, dRep); //do stestowania
		actions.add(fad);
		FindDocByIdAction fdbi = new FindDocByIdAction(sv, dRep); //do stestowania
		actions.add(fdbi);
		LoginAction la = new LoginAction(sv, pRep);
		actions.add(la);
		AssignUserToProjectAction autp = new AssignUserToProjectAction(sv, pRep);
		actions.add(autp);
		AssignUserToDocAction autd = new AssignUserToDocAction(pRep);
		actions.add(autd);
		LogoutAction logoutAction = new LogoutAction(sv);
		actions.add(logoutAction);
		FindProjectsOfUserAction fpou = new FindProjectsOfUserAction(sv, pr);
		actions.add(fpou);
		AssignDocumentToProjectAction adtp = new AssignDocumentToProjectAction(sv,pr);
		actions.add(adtp);
		FindDocumentsOfProjectAction fdop = new FindDocumentsOfProjectAction(sv, dRep);
		actions.add(fdop);
	}
}
