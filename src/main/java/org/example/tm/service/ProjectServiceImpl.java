package org.example.tm.service;

import org.example.tm.baseApp.repository.IProjectRepository;
import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectServiceImpl extends AbstractBaseServiceImpl<Project, IProjectRepository> implements IProjectService {
    public ProjectServiceImpl(IProjectRepository baseR) {
        super(baseR);
    }

    @Override
    public void setUser(User user) {
        baseRep.setUser(user);
    }

    @Override
    public @NotNull List<Project> findByPart(@NotNull String searchString) {
        return baseRep.findByPart(searchString);
    }

    @Override
    public @NotNull List<Project> findByUserId() {
        return baseRep.findByUserId();
    }
}
