package spring.reactjs.demo.ppmtool.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import spring.reactjs.demo.ppmtool.domain.Project;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createNewProject() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Project p =new Project();
        p.setProjectName("Test project");
        p.setDescription("Test description");
        p.setProjectIdentifier("IdteB");
        p.setStart_date(new Date());
        p.setEnd_date((new Date()));

        HttpEntity<Project> entity = new HttpEntity<Project>(p, headers);

        String urlString = "http://localhost:"+port+"/api/project";

        // send request and parse result
        ResponseEntity<Project> loginResponse = restTemplate
                .exchange(urlString, HttpMethod.POST, entity, Project.class);
        if (loginResponse.getStatusCode() == HttpStatus.CREATED) {
            Project retProject=loginResponse.getBody();
            assertThat(retProject.getProjectIdentifier().toUpperCase()).isEqualTo(p.getProjectIdentifier().toUpperCase());
        }else{
            //System.out.println(loginResponse.getBody().toString());
            fail(loginResponse.getBody().toString());
        }
        //assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
        //        String.class)).contains("Hello World");
    }
}