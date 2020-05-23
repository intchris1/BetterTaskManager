package org.example.tm.baseApp.service;

import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface IProjectService extends IBaseService<Project> {

    void setUser(User user);

    @NotNull
    Map<String, Project> sortByDateStart();

    @NotNull
    Map<String, Project> sortByDateFinish();

}
