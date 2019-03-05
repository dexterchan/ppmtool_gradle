package spring.reactjs.demo.ppmtool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.reactjs.demo.ppmtool.domain.Project;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String projectIdentifier);

    @Override
    public Iterable<Project> findAll();
}
