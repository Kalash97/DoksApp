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
import com.doksapp.controller.actions.EnableAdminAction;
import com.doksapp.controller.actions.EnableDocumentsAction;
import com.doksapp.controller.actions.EnableProjectsAction;
import com.doksapp.controller.actions.FindAllDocsAction;
import com.doksapp.controller.actions.FindAllProjectsAction;
import com.doksapp.controller.actions.FindDocByIdAction;
import com.doksapp.controller.actions.FindDocumentsOfProjectAction;
import com.doksapp.controller.actions.FindDocumentsOfUserAction;
import com.doksapp.controller.actions.FindOrphanedProjectsAction;
import com.doksapp.controller.actions.FindProjectByIdAction;
import com.doksapp.controller.actions.FindProjectsOfUserAction;
import com.doksapp.controller.actions.LoginAction;
import com.doksapp.controller.actions.LogoutAction;
import com.doksapp.controller.actions.RedirectAction;
import com.doksapp.controller.actions.RegisterAction;
import com.doksapp.controller.actions.RemoveDocumentFromProjectAction;
import com.doksapp.controller.actions.RemoveDocumentFromUserAction;
import com.doksapp.controller.actions.RemoveProjectFromUserAction;
import com.doksapp.controller.actions.SafeDeleteOfProjectAction;
import com.doksapp.controller.actions.UpdateDocumentAction;
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
		System.out.println("---------------req:"+req+ "   res:"+res);
		
		prepareActions(req, res);
		
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
		DocumentRepository dRep = new DocumentRepository(hpm);
		PersonRepository pRep = new PersonRepository(hpm);
		ServletView sv = new ServletView(req, res);
		
		CreateProjectAction cpa = new CreateProjectAction(sv,pr);
		RegisterAction ra = new RegisterAction(sv, pRep);
		DeleteProjectAction dp = new DeleteProjectAction(sv, pr);
		CreateDocumentAction cda = new CreateDocumentAction(sv,dRep);
		UpdateProjectAction upa = new UpdateProjectAction(sv, pr);
		RedirectAction redirectA = new RedirectAction(sv);
		DeleteDocumentAction dda = new DeleteDocumentAction(sv, dRep);
		FindAllProjectsAction fap = new FindAllProjectsAction(sv, pr);
		FindProjectByIdAction fpbi = new FindProjectByIdAction(sv, pr);
		FindAllDocsAction fad = new FindAllDocsAction(sv, dRep); 
		FindDocByIdAction fdbi = new FindDocByIdAction(sv, dRep);
		LoginAction la = new LoginAction(sv, pRep);
		AssignUserToProjectAction autp = new AssignUserToProjectAction(sv, pRep);
		AssignUserToDocAction autd = new AssignUserToDocAction(sv,pRep);
		LogoutAction logoutAction = new LogoutAction(sv);
		FindProjectsOfUserAction fpou = new FindProjectsOfUserAction(sv, pr);
		AssignDocumentToProjectAction adtp = new AssignDocumentToProjectAction(sv,pr);
		FindDocumentsOfProjectAction fdop = new FindDocumentsOfProjectAction(sv, dRep);
		UpdateDocumentAction uda = new UpdateDocumentAction(sv, dRep);
		EnableAdminAction eaa = new EnableAdminAction(sv);
		FindDocumentsOfUserAction fdou = new FindDocumentsOfUserAction(sv,dRep);
		EnableProjectsAction epa = new EnableProjectsAction(sv);
		EnableDocumentsAction eda = new EnableDocumentsAction(sv);
		RemoveDocumentFromProjectAction rdfp = new RemoveDocumentFromProjectAction(sv, pr);
		RemoveProjectFromUserAction rpfu = new RemoveProjectFromUserAction(sv, pRep);
		RemoveDocumentFromUserAction rdfu = new RemoveDocumentFromUserAction(sv, pRep);
		SafeDeleteOfProjectAction sdop = new SafeDeleteOfProjectAction(sv, pr, pRep);
		FindOrphanedProjectsAction fop = new FindOrphanedProjectsAction(pr);
		
		actions.add(fop);
		actions.add(sdop);
		actions.add(rdfu);
		actions.add(rpfu);
		actions.add(rdfp);
		actions.add(eda);
		actions.add(epa);
		actions.add(fdou);
		actions.add(cda);
		actions.add(ra);
		actions.add(dp);
		actions.add(cpa);
		actions.add(upa);
		actions.add(dda);
		actions.add(redirectA);
		actions.add(fap);
		actions.add(la);
		actions.add(fpbi);
		actions.add(fdbi);
		actions.add(fad);
		actions.add(autp);
		actions.add(autd);
		actions.add(logoutAction);
		actions.add(fpou);
		actions.add(adtp);
		actions.add(fdop);
		actions.add(uda);
		actions.add(eaa);
	}
}
