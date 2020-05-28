package org.example.tm.baseApp.repository;

import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends IUsersEntityRepository<Task> {

    @NotNull List<Task> findAllByUserIdAndProjectId(@NotNull String userId, @NotNull String projectId);

    void deleteAllByUserIdAndProjectId(@NotNull String userId, @NotNull String projectId);

}
