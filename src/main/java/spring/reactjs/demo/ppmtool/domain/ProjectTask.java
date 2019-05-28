package spring.reactjs.demo.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String projectSequence;

    @NotBlank(message = "Please include a summary")
    private String summary;

    private String acceptanceCriteria;

    private String status;

    private Integer priority;

    private Date dueDate;

    //Many to One with backlog
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="backlog_id",updatable = false, nullable = false)
    @JsonIgnore //avoid endless looping
    private BackLog backlog;


    @Column(updatable = false)
    private String projectIdentifier;

    private Date create_At;
    private Date update_At;

    public ProjectTask(){

    }

    @PrePersist
    protected void onCreate(){
        this.create_At=new Date();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.update_At=new Date();
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                '}';
    }

    public BackLog getBacklog() {
        return backlog;
    }

    public void setBacklog(BackLog backlog) {
        this.backlog = backlog;
    }
}
