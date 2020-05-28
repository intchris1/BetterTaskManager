package org.example.tm.service;

import org.example.tm.baseApp.repository.IProjectRepository;
import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectUsersEntityServiceImpl extends AbstractUsersEntityServiceImpl<Project, IProjectRepository> implements IProjectService {

    public ProjectUsersEntityServiceImpl(IProjectRepository baseR) {
        super(baseR);
    }

}
