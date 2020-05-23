package org.example.tm.repository;

import org.example.tm.baseApp.repository.ITaskRepository;
import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TaskRepositoryImpl extends AbstractBaseRepositoryImpl<Task> implements ITaskRepository {
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void removeByProjectId(@NotNull String projectId) {
        entities.entrySet().removeIf(k -> k.getValue().getProjectId().equals(projectId));
    }

    @Nullable
    @Override
    public Map<String, Task> getByProjectId(@NotNull String projectId) {
        Map<String, Task> tasksForProject = new HashMap<>();
        for (Map.Entry<String, Task> stringTaskEntry : entities.entrySet()) {
            String key = stringTaskEntry.getKey();
            Task task = stringTaskEntry.getValue();
            if(task.getProjectId().equals(projectId)) tasksForProject.put(key, task);
        }
        return tasksForProject;
    }

    @Nullable
    @Override
    public @NotNull Map<String, Task> sortByDateStart(@NotNull String currentUserId) {
        return null;
    }

    @Nullable
    @Override
    public @NotNull Map<String, Task> sortByDateFinish(@NotNull String currentUserId) {
        return null;
    }

    @Nullable
    @Override
    public @NotNull Map<String, Task> findAll() {
        Map<String, Task> tasksForUser = new HashMap<>();
        for (Map.Entry<String, Task> stringTaskEntry : entities.entrySet()) {
            String key = stringTaskEntry.getKey();
            Task task = stringTaskEntry.getValue();
            if(task.getUserId().equals(currentUser.getId())) tasksForUser.put(key, task);
        }
        return tasksForUser;
    }


    @Nullable
    @Override
    public Task findOneByName(@NotNull String name) {
        for (Map.Entry<String, Task> stringTaskEntry : entities.entrySet()) {
            Task task = stringTaskEntry.getValue();
            if(task.getName().equals(name) && task.getUserId().equals(currentUser.getId())) return task;
        }
        return null;
    }
}
