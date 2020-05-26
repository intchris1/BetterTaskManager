package org.example.tm.baseApp.repository;

import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IProjectRepository extends IBaseRepository<Project> {

    void setUser(User user);

    @NotNull List<Project> findByPart(@NotNull String searchString);

    @NotNull
    List<Project> findByUserId();
}
