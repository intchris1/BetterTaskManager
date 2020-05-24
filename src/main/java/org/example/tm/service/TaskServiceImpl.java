package org.example.tm.service;


import org.example.tm.baseApp.repository.ITaskRepository;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskServiceImpl extends AbstractBaseServiceImpl<Task, ITaskRepository> implements ITaskService {
    public TaskServiceImpl(ITaskRepository baseRep) {
        super(baseRep);
    }

    @Override
    public void setUser(User user) {
        baseRep.setUser(user);
    }


    @Override
    public @NotNull List<Task> getByProjectId(@NotNull String projectId) {
        return baseRep.getByProjectId(projectId);
    }

    @Override
    public void removeByProjectId(@NotNull String projectId) {
        baseRep.removeByProjectId(projectId);
    }

    @Override
    public @NotNull List<Task> findByPart(@NotNull String searchString) {
        return baseRep.findByPart(searchString);
    }
}



