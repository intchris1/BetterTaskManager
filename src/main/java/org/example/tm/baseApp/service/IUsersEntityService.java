package org.example.tm.baseApp.service;


import org.example.tm.entity.UsersAbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IUsersEntityService<T extends UsersAbstractEntity> {

    @NotNull
    T findByName(@NotNull String userId, @Nullable String name) throws Exception;

    void createNewEntity(@NotNull T t, @NotNull String userId, @Nullable String name) throws Exception;

    void updateName(@NotNull String userId, @Nullable String oldName, @Nullable String newName) throws Exception;

    @NotNull List<T> searchByPart(@NotNull String userId, @Nullable String searchString) throws Exception;

    @NotNull List<T> findAllOrderByStatus(@NotNull String userId);

    @NotNull List<T> findAllOrderByName(@NotNull String userId);

    @NotNull List<T> findAllOrderByStartDate(@NotNull String userId);

    @NotNull List<T> findAllOrderByEndDate(@NotNull String userId);

    @NotNull List<T> findAllOrderByCreationDate(@NotNull String userId);

    void deleteByName(@NotNull String userId, @Nullable String name) throws Exception;

    void deleteAllByUserId(@NotNull String userId);

    @NotNull List<T> findAll();

    void saveAll(@NotNull List<T> list);

    void deleteAll();

}
