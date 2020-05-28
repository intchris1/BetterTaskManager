package org.example.tm.baseApp.service;

import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ITaskService extends IUsersEntityService<Task> {

    @NotNull List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    void deleteAllByProjectId(@NotNull String userId, @NotNull String projectId);

}
