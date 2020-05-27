package org.example.tm.config;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.service.SubjectAreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SubjectAreaConfig {

    @Autowired
    IProjectService projectService;
    @Autowired
    ITaskService taskService;
    @Autowired
    IUserService userService;

    @Bean
    @Scope(value = "prototype")
    public ISubjectAreaService subjectAreaService() {
        return new SubjectAreaServiceImpl(projectService, taskService, userService);
    }

}
