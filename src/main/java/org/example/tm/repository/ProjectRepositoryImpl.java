package org.example.tm.repository;

import org.example.tm.baseApp.repository.IProjectRepository;
import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public final class ProjectRepositoryImpl extends AbstractBaseRepositoryImpl<Project> implements IProjectRepository {
    private User currentUser;

    @Override
    public Project findOneByName(@NotNull String name) {
        for (Map.Entry<String, Project> stringProjectEntry : entities.entrySet()) {
            Project project = stringProjectEntry.getValue();
            if (project.getName().equals(name) && project.getUserId().equals(currentUser.getId())) return project;
        }
        return null;
    }

    @Override
    public @NotNull List<Project> findByUserId() {
        List<Project> projectsForUser = new ArrayList<>();
        for (Map.Entry<String, Project> stringProjectEntry : entities.entrySet()) {
            Project project = stringProjectEntry.getValue();
            if (project.getUserId().equals(currentUser.getId())) projectsForUser.add(project);
        }
        return projectsForUser;
    }

    @Override
    public void setUser(User user) {
        this.currentUser = user;
    }

    @Override
    public @NotNull List<Project> findByPart(@NotNull String searchString) {
        List<Project> projects = new ArrayList<>();
        for (Map.Entry<String, Project> stringProjectEntry : entities.entrySet()) {
            Project project = stringProjectEntry.getValue();
            if(project.getName().contains(searchString) || project.getDescription().contains(searchString)) {
                projects.add(project);
            }
        }
        return projects;
    }

}
