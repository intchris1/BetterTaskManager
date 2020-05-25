package org.example.tm.baseApp.service;

import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IBaseService<T extends AbstractEntity> {
    @NotNull
    List<T> findAll();

    @Nullable
    T findOneByName(@Nullable final String name);

    @Nullable
    T save(@NotNull final T entity);

    void save(@NotNull final List<T> entities);

    @Nullable
    T update(@NotNull final T entity);

    void remove(@NotNull final T entity);

    void removeAll();
}
