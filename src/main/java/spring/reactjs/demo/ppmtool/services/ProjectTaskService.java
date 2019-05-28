package spring.reactjs.demo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.reactjs.demo.ppmtool.domain.ProjectTask;
import spring.reactjs.demo.ppmtool.repositories.BackLogRepository;
import spring.reactjs.demo.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

    @Autowired
    private BackLogRepository backLogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(){
        //PT to be added to a specific project, project != null, BL exists
        // set the backlog to project
        //project sequence to be like <projectid>-<number>
        //Update the backlog sequence

        //Initial priority when priority null
        //Initial Status when status is null

        return null;
    }

}
