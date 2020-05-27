package org.example.tm.repository;

import org.example.tm.baseApp.repository.ITaskRepository;
import org.example.tm.entity.Task;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TaskRepositoryImpl extends AbstractBaseRepositoryImpl<Task> implements ITaskRepository {
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void removeByProjectId(@NotNull String projectId) {
        entities.entrySet().removeIf(k -> k.getValue().getProjectId().equals(projectId));
    }

    @Override
    public @NotNull List<Task> getByProjectId(@NotNull String projectId) {
        List<Task> tasksForProject = new ArrayList<>();
        for (Map.Entry<String, Task> stringTaskEntry : entities.entrySet()) {
            Task task = stringTaskEntry.getValue();
            if (task.getProjectId().equals(projectId)) tasksForProject.add(task);
        }
        return tasksForProject;
    }

    @Override
    public @NotNull List<Task> findByPart(@NotNull String searchString) {
        List<Task> tasks = new ArrayList<>();
        for (Map.Entry<String, Task> stringTaskEntry : entities.entrySet()) {
            Task task = stringTaskEntry.getValue();
            if(task.getName().contains(searchString) || task.getDescription().contains(searchString)) {
                tasks.add(task);
            }
        }
        return tasks;
    }


    @Nullable
    @Override
    public @NotNull List<Task> findByUserId() {
        List<Task> tasksForUser = new ArrayList<>();
        for (Map.Entry<String, Task> stringTaskEntry : entities.entrySet()) {
            Task task = stringTaskEntry.getValue();
            if (task.getUserId().equals(currentUser.getId())) tasksForUser.add(task);
        }
        return tasksForUser;
    }


    @Nullable
    @Override
    public Task findOneByName(@NotNull String name) {
        for (Map.Entry<String, Task> stringTaskEntry : entities.entrySet()) {
            Task task = stringTaskEntry.getValue();
            if (task.getName().equals(name) && task.getUserId().equals(currentUser.getId())) return task;
        }
        return null;
    }
}
