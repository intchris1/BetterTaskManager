package org.example.tm.baseApp.service;

import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface ITaskService extends IBaseService<Task> {

    void setUser(User user);

    @Nullable
    Map<String, Task> getByProjectId(String projectId);

    void removeByProjectId(String projectId);

    @NotNull
    Map<String, Task> sortByDateStart(@NotNull final String currentUserId);

    @NotNull
    Map<String, Task> sortByDateFinish(@NotNull final String currentUserId);
}
