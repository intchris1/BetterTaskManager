package org.example.tm.service;


import org.example.tm.baseApp.repository.ITaskRepository;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TaskServiceImpl extends AbstractBaseServiceImpl<Task, ITaskRepository> implements ITaskService {
    public TaskServiceImpl(ITaskRepository baseRep) {
        super(baseRep);
    }

    @Override
    public void setUser(User user) {
        baseRep.setUser(user);
    }


    @Override
    public Map<String, Task> getByProjectId(@NotNull String projectId) {
        if(projectId == null || projectId.isEmpty()) return null;
        return baseRep.getByProjectId(projectId);
    }

    @Override
    public void removeByProjectId(@NotNull String projectId) {
        baseRep.removeByProjectId(projectId);
    }

    @Override
    public @NotNull Map<String, Task> sortByDateStart(@NotNull String currentUserId) {
        return null;
    }

    @Override
    public @NotNull Map<String, Task> sortByDateFinish(@NotNull String currentUserId) {
        return null;
    }
}
