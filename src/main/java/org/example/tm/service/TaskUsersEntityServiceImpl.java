package org.example.tm.service;


import org.example.tm.baseApp.repository.ITaskRepository;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskUsersEntityServiceImpl extends AbstractUsersEntityServiceImpl<Task, ITaskRepository> implements ITaskService {

    public TaskUsersEntityServiceImpl(ITaskRepository rep) {
        super(rep);
    }


    @Override
    public @NotNull List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        return rep.findAllByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public void deleteAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        rep.deleteAllByUserIdAndProjectId(userId, projectId);
    }
}



