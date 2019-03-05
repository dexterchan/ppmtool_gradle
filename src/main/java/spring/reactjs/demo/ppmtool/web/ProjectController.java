package spring.reactjs.demo.ppmtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import spring.reactjs.demo.ppmtool.domain.Project;
import spring.reactjs.demo.ppmtool.exceptions.ProjectIdException;
import spring.reactjs.demo.ppmtool.services.MapValidationErrorService;
import spring.reactjs.demo.ppmtool.services.ProjectService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody  Project project, BindingResult result){

        ResponseEntity<?> r=mapValidationService.mapValidationService(result);
        if(r!=null){
            return r;
        }

        Project prj1=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(prj1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project Id "+projectId+" not exist");
        }
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project>getAllProjects(){
        return projectService.findAllProjects();
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?>deleteProjectById(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());

        return new ResponseEntity<String>("Project with ID:"+projectId+" was deleted",HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?>updateProject(@Valid @RequestBody  Project project, BindingResult result) {

        ResponseEntity<?> r=mapValidationService.mapValidationService(result);
        if(r!=null){
            return r;
        }
        Project prj1=projectService.updateProjectByIdentifier(project);
        return new ResponseEntity<Project>(prj1, HttpStatus.OK);

    }
}
