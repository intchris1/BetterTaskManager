package org.example.tm.baseApp.repository;

import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ITaskRepository extends IBaseRepository<Task> {

    void setUser(User user);

    void removeByProjectId(@NotNull String projectId);

    @Nullable
    List<Task> getByProjectId(@NotNull String projectId);

}
