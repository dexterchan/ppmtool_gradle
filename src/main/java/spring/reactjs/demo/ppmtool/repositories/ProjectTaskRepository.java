package spring.reactjs.demo.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.reactjs.demo.ppmtool.domain.ProjectTask;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {

    public Iterable<ProjectTask> findByProjectIdentifierOrderByPriority(String id) ;
}