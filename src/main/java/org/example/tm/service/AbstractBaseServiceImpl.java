package org.example.tm.service;


import org.example.tm.baseApp.repository.IBaseRepository;
import org.example.tm.baseApp.service.IBaseService;
import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractBaseServiceImpl<T extends AbstractEntity, R extends IBaseRepository<T>> implements IBaseService<T> {

    protected final R baseRep;

    public AbstractBaseServiceImpl(R baseRep) {
        this.baseRep = baseRep;
    }

    @Override
    public @NotNull List<T> findAll() {
        return baseRep.findAll();
    }

    @Nullable
    @Override
    public T findOneByName(@Nullable String name) {
        if (name == null || name.isEmpty()) return null;
        return baseRep.findOneByName(name);
    }

    @Nullable
    @Override
    public T save(@NotNull T t) {
        if (t.getName().isEmpty()) return null;
        if (findOneByName(t.getName()) != null) return null;
        baseRep.persist(t);
        return t;
    }

    @Override
    public void save(@NotNull List<T> entities) {
        baseRep.persist(entities);
    }

    @Nullable
    @Override
    public T update(@NotNull T t) {
        if (t.getName().isEmpty()) return null;
        if (findOneByName(t.getName()) != null) return null;
        return baseRep.merge(t);
    }

    @Override
    public void remove(@NotNull T t) {
        baseRep.remove(t);
    }

    @Override
    public void removeAll() {
        baseRep.removeAll();
    }
}
