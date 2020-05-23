package org.example.tm.repository;

import org.example.tm.baseApp.repository.IProjectRepository;
import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

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
    public @NotNull Map<String, Project> findAll() {
        Map<String, Project> projectsForUser = new HashMap<>();
        for (Map.Entry<String, Project> stringProjectEntry : entities.entrySet()) {
            String key = stringProjectEntry.getKey();
            Project project = stringProjectEntry.getValue();
            if (project.getUserId().equals(currentUser.getId())) projectsForUser.put(key, project);
        }
        return projectsForUser;
    }

    @Override
    public void setUser(User user) {
        this.currentUser = user;
    }

    @NotNull
    @Override
    public Map<String, Project> sortByDateStart() {
        return null;
    }

    @NotNull
    @Override
    public Map<String, Project> sortByDateFinish() {
        return null;
    }
}
