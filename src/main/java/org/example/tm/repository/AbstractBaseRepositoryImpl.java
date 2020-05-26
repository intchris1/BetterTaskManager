package org.example.tm.repository;

import org.example.tm.baseApp.repository.IBaseRepository;
import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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
    public List<T> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Nullable
    @Override
    public abstract T findOneByName(@NotNull String name);

    @Override
    public void persist(@NotNull T entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void persist(@NotNull List<T> list) {
        entities.clear();
        list.forEach(entity -> entities.put(entity.getId(), entity));
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
