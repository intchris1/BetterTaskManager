package org.example.tm.baseApp.service;

import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ITaskService extends IBaseService<Task> {

    void setUser(User user);

    @NotNull
    List<Task> getByProjectId(String projectId);

    void removeByProjectId(String projectId);
}
