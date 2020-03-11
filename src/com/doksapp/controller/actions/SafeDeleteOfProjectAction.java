package com.doksapp.controller.actions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.doksapp.model.entities.AccountType;
import com.doksapp.model.entities.Persistable;
import com.doksapp.model.entities.Person;
import com.doksapp.model.entities.Project;
import com.doksapp.model.repositories.PersonRepository;
import com.doksapp.model.repositories.ProjectRepository;
import com.doksapp.view.ServletView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SafeDeleteOfProjectAction implements Action{

	private ServletView view;
	private ProjectRepository projectRepo;
	private PersonRepository personRepo;
	
	@Override
	public void launch() {
		String idProject = view.getId();
		long idProjectLong = Long.parseLong(idProject);
		
		////pobraæ userów
		//userRepo.findUsersHavingProject
		List<Persistable> usersHavingProject = personRepo.findUsersHavingProject(idProject);
		List<Person> persons = usersHavingProject.stream().map(p->(Person)p).collect(Collectors.toList());
		
		////odpi¹æ projekt userom
		//fory z hpm
		
		Project ppx=null;
		
		for(Person p : persons) {
			for(Project pr : p.getProjects()) {
				if(pr.getId()==idProjectLong) {
					ppx=pr;
					p.getProjects().remove(pr);
				}
				break;
			}
			personRepo.updateEntity(p);
			System.out.println(Arrays.toString(p.getProjects().toArray()));
		}
		
	
		////wyszukaæ projekt
		//projectRepo.findById
//		Project project = (Project) projectRepo.readProjectsById(idProject);
//		System.out.println("Project: "+project);
		
		////odpi¹æ wszystkie dokumenty
		//project.getDoc().remove all elements
//		if(project.getDocuments().size()>0) {	
//			for(int j=0; j<project.getDocuments().size(); j++) {
//				project.getDocuments().remove(j);
//			}
//		}
		
		if(ppx.getDocuments().size()>0) {	
			for(int j=0; j<ppx.getDocuments().size(); j++) {
				ppx.getDocuments().remove(j);
			}
		}
		
		//System.out.println(Arrays.toString(project.getDocuments().toArray()));
		
		////usun¹æ projekt
		//projectRepo.delete
		//Persistable p = project;
		//projectRepo.deleteProjectSafe(project);//java.lang.IllegalArgumentException: Removing a detached instance com.doksapp.model.entities.Project#3
		//projectRepo.deleteProjectSafe(ppx);
		//projectRepo.deleteProject(idProjectLong);//nie usuwa tego samego bo chyba robi 2 strza³ do bazy
		//projectRepo.deleteProjectByObject(project);
		
		//repo.safeDeleteOfProject(idProject);
	}

	@Override
	public String getName() {
		return "SafeDeleteOfProject";
	}

	@Override
	public List<AccountType> getAllowedRoles() {
		return Arrays.asList(new AccountType[]{AccountType.ADMIN, AccountType.MANAGER, AccountType.WORKER});
	}

}
