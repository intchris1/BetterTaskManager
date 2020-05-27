package org.example.tm.service;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.entity.Project;
import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonRootName("SubjectAreaServiceImpl")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SubjectAreaServiceImpl")
public class SubjectAreaServiceImpl implements ISubjectAreaService, Serializable {

    private IProjectService projectService;
    private ITaskService taskService;
    private IUserService userService;

    public SubjectAreaServiceImpl(IProjectService projectService, ITaskService taskService, IUserService userService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
    }

    private static final long serialVersionUID = 1;

    @Nullable
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    @JacksonXmlProperty(localName = "project")
    @JacksonXmlElementWrapper(localName = "projects")
    private List<Project> projectList = new ArrayList<>();

    @Nullable
    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks")
    @JacksonXmlProperty(localName = "task")
    @JacksonXmlElementWrapper(localName = "tasks")
    private List<Task> taskList = new ArrayList<>();

    @Nullable
    @XmlElement(name = "user")
    @XmlElementWrapper(name = "users")
    @JacksonXmlProperty(localName = "user")
    @JacksonXmlElementWrapper(localName = "users")
    private List<User> userList = new ArrayList<>();


    public void write() {
        if (projectList != null) projectService.save(projectList);
        if (taskList != null) taskService.save(taskList);
        if (userList != null) userService.save(userList);
    }

    public void read() {
        projectList = projectService.findAll();
        taskList = taskService.findAll();
        userList = userService.findAll();
    }

}
