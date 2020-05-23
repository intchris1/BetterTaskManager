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

    @Nullable
    T persist(@NotNull final T entity);

    @Nullable
    T merge(@NotNull final T entity);

    void remove(@NotNull final T entity);

    void removeAll();
}
