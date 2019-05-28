package spring.reactjs.demo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.reactjs.demo.ppmtool.domain.BackLog;
import spring.reactjs.demo.ppmtool.domain.ProjectTask;
import spring.reactjs.demo.ppmtool.repositories.BackLogRepository;
import spring.reactjs.demo.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

    @Autowired
    private BackLogRepository backLogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //PT to be added to a specific project, project != null, BL exists
        BackLog backlog = backLogRepository.findByProjectIdentifier(projectIdentifier);
        // set the backlog to project
        projectTask.setBacklog(backlog);
        //project sequence to be like <projectid>-<number>
        Integer BacklogSequence = backlog.getPTSequence();
        //Update the backlog sequence
        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);

        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //Initial priority when priority null
        if(projectTask.getPriority()==null || projectTask.getPriority()==0 ){
            projectTask.setPriority(3);
        }
        //Initial Status when status is null
        if( projectTask.getStatus()==null || projectTask.getStatus()=="" ){
            projectTask.setStatus("TO_DO");
        }



        return projectTaskRepository.save(projectTask);
    }

}
