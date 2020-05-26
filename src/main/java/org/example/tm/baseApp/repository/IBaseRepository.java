package org.example.tm.baseApp.repository;

import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IBaseRepository<T extends AbstractEntity> {
    @NotNull
    List<T> findAll();

    @Nullable
    T findOneByName(@NotNull final String name);

    void persist(@NotNull final T entity);

    void persist(@NotNull final List<T> entities);

    @Nullable
    T merge(@NotNull final T entity);

    void remove(@NotNull final T entity);

    void removeAll();
}
