package org.example.tm.repository;

import org.example.tm.baseApp.repository.IBaseRepository;
import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseRepositoryImpl<T extends AbstractEntity> implements IBaseRepository<T> {
    protected final Map<String, T> entities;

    public AbstractBaseRepositoryImpl() {
        this.entities = new HashMap<>();
    }

    @NotNull
    @Override
    public abstract List<T> findAll();

    @Nullable
    @Override
    public abstract T findOneByName(@NotNull String name);

    @Nullable
    @Override
    public T persist(@NotNull T entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Nullable
    @Override
    public T merge(@NotNull T entity) {
        return entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(@NotNull T entity) {
        entities.remove(entity.getId());
    }

    @Override
    public void removeAll() {
        entities.clear();
    }
}
