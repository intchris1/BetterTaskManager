package org.example.tm.baseApp.repository;

import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface IProjectRepository extends IBaseRepository<Project> {

    void setUser(User user);

    @NotNull
    Map<String, Project> sortByDateStart();

    @NotNull
    Map<String, Project> sortByDateFinish();
}
