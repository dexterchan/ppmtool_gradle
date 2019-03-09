package spring.reactjs.demo.ppmtool.services;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.reactjs.demo.ppmtool.domain.Project;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void returnfailurewithoutidentifiersaveOrUpdateProject() {
        System.out.println("Hello testing");

        Project p =new Project();
        p.setProjectName("Test project");
        p.setDescription("Test description");
        p.setStart_date(new Date());
        p.setEnd_date((new Date()));
        try {
            projectService.saveOrUpdateProject(p);
            fail("should throw error");
        }catch(Exception ex){
            assertThat(ex.getMessage()).isEqualTo("Null project identifier");
        }

    }
}