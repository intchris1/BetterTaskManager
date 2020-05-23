package org.example.tm.service;

import org.example.tm.baseApp.repository.IProjectRepository;
import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;

public class ProjectServiceImpl extends AbstractBaseServiceImpl<Project, IProjectRepository> implements IProjectService {
    public ProjectServiceImpl(IProjectRepository baseR) {
        super(baseR);
    }

    @Override
    public void setUser(User user) {
        baseRep.setUser(user);
    }

}
