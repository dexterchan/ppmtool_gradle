package spring.reactjs.demo.ppmtool.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import spring.reactjs.demo.ppmtool.domain.Project;
import spring.reactjs.demo.ppmtool.services.ProjectService;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class MockProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService service;

    @Test
    public void createNewProject() {
        Project p =new Project();
        p.setProjectName("Test project");
        p.setDescription("Test description");
        p.setProjectIdentifier("IdteB");
        p.setStart_date(new Date());
        p.setEnd_date((new Date()));
        when(service.saveOrUpdateProject( p )).thenReturn(p);

       // this.mockMvc.perform(post("/api/project")).andDo(print()).andExpect(status().isCreated())
         //       .andExpect(content().string(containsString("Hello Mock")));

    }
}