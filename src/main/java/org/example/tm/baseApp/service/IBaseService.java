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
    T save(@Nullable final T entity);

    @Nullable
    T update(@Nullable final T entity);

    void remove(@Nullable final T entity);

    void removeAll();
}
