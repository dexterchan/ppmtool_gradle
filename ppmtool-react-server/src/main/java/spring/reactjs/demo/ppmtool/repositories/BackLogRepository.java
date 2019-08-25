package spring.reactjs.demo.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.reactjs.demo.ppmtool.domain.BackLog;

@Repository
public interface BackLogRepository extends CrudRepository<BackLog,Long> {

    BackLog findByProjectIdentifier(String projectIdentifier);
}
