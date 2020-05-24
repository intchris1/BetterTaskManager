package org.example.tm.baseApp.service;

import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IProjectService extends IBaseService<Project> {

    void setUser(User user);

    @NotNull List<Project> findByPart(@NotNull String searchString);
}
