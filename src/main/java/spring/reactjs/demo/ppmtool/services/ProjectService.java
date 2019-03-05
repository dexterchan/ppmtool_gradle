package spring.reactjs.demo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.reactjs.demo.ppmtool.domain.Project;
import spring.reactjs.demo.ppmtool.exceptions.ProjectIdException;
import spring.reactjs.demo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        //Logic
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIdException("ProjectID "+project.getProjectIdentifier().toUpperCase()+ " already exists");
        }

    }

    public Project findProjectByIdentifier(String projectId){
        return projectRepository.findByProjectIdentifier((projectId));
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectid){
        Project project = this.findProjectByIdentifier(projectid);

        if(project == null){
            throw new ProjectIdException("Cannot delete project with id:"+projectid+". This project does not exists");
        }
        projectRepository.delete(project);
    }

    public Project updateProjectByIdentifier(Project project){
        String projectid=project.getProjectIdentifier().toUpperCase();
        Project project1 = this.findProjectByIdentifier(projectid);

        if(project1 == null){
            throw new ProjectIdException("Cannot update project with id:"+projectid+". This project does not exists");
        }
        project.setId(project1.getId());
        return projectRepository.save(project);
    }
}
