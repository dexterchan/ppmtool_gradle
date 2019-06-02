package spring.reactjs.demo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.reactjs.demo.ppmtool.domain.BackLog;
import spring.reactjs.demo.ppmtool.domain.Project;
import spring.reactjs.demo.ppmtool.domain.ProjectTask;
import spring.reactjs.demo.ppmtool.exceptions.ProjectIdException;
import spring.reactjs.demo.ppmtool.exceptions.ProjectNotFoundException;
import spring.reactjs.demo.ppmtool.repositories.BackLogRepository;
import spring.reactjs.demo.ppmtool.repositories.ProjectRepository;
import spring.reactjs.demo.ppmtool.repositories.ProjectTaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectTaskService {

    @Autowired
    private BackLogRepository backLogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String _projectIdentifier, ProjectTask projectTask){
        String projectIdentifier = _projectIdentifier.toUpperCase();
        try {
            //PT to be added to a specific project, project != null, BL exists
            BackLog backlog = backLogRepository.findByProjectIdentifier(projectIdentifier);
            // set the backlog to project
            projectTask.setBacklog(backlog);
            //project sequence to be like <projectid>-<number>
            Integer BacklogSequence = backlog.getPTSequence();
            //Update the backlog sequence
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);

            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            //Initial priority when priority null
            if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
                projectTask.setPriority(3);
            }
            //Initial Status when status is null
            if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
                projectTask.setStatus("TO_DO");
            }


            return projectTaskRepository.save(projectTask);
        }catch(Exception e){
            throw new ProjectNotFoundException("Project ("+projectIdentifier+") Not found");
        }
    }

    public Iterable<ProjectTask> findBackLogById(String id) {
        try {
            Optional<Project> p = Optional.of(projectRepository.findByProjectIdentifier(id.toUpperCase()));
        }catch(NullPointerException ne){
            throw new ProjectNotFoundException("Project ("+id+") not found");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String _backlog_id,String pt_id){
        String backlog_id = _backlog_id.toUpperCase();
        Optional<BackLog> b=null;
        ProjectTask _projectTask=null;
        //make sure we are searching on an existing backlog
        try {
            b = Optional.of(backLogRepository.findByProjectIdentifier(backlog_id));
        }catch(NullPointerException ne){
            throw new ProjectNotFoundException("Back log of Project id ("+backlog_id+") not found");
        }

        //make sure our task exists
        Optional<ProjectTask> pt = Optional.ofNullable(projectTaskRepository.findByProjectSequence(pt_id));


        if(pt.isPresent()){
            _projectTask = pt.get();
            //make sure that backlog/project id is the path coorespond to its project
            if(!_projectTask.getProjectIdentifier().equals(backlog_id)){
                throw new ProjectNotFoundException("Project Task ("+pt_id+") does not exist in project("+backlog_id+")");
            }
        }else{
            throw new ProjectNotFoundException("Project Task("+pt_id+") not found");
        }


        return _projectTask;

    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String _backlog_id,String pt_id){
        ProjectTask pt = this.findPTByProjectSequence(_backlog_id,pt_id );
        updatedTask.setProjectIdentifier(_backlog_id);
        updatedTask.setProjectSequence(pt_id);

        return  projectTaskRepository.save(updatedTask);
    }

    public void deleteByProjectSequence(String _backlog_id,String pt_id){
        ProjectTask pt = this.findPTByProjectSequence(_backlog_id,pt_id );

        BackLog backlog = pt.getBacklog();
        List<ProjectTask> pts = backlog.getProjectTasks();
        pts.remove(pt);
        backLogRepository.save(backlog);
        //Not working with Spring cascade = CascadeType.REFRESH
        //Need to remove the project task from backlog first....
        //then remove the project task
        //either missing not working
         projectTaskRepository.delete(pt);
    }
}
