package org.example.tm.service;

import org.example.tm.baseApp.repository.IUsersEntityRepository;
import org.example.tm.baseApp.service.IUsersEntityService;
import org.example.tm.entity.UsersAbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUsersEntityServiceImpl<T extends UsersAbstractEntity, R extends IUsersEntityRepository<T>> implements IUsersEntityService<T> {

    protected final R rep;

    protected AbstractUsersEntityServiceImpl(R rep) {
        this.rep = rep;
    }


    @NotNull
    @Override
    public T findByName(@NotNull String userId, @Nullable String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("Name is empty");
        T t = rep.findByUserIdAndName(userId, name);
        if (t == null) throw new Exception("Name doesn't exist");
        return t;
    }

    public void createNewEntity(@NotNull T t, @NotNull String userId, @Nullable String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("Name is empty");
        if (rep.existsByUserIdAndName(userId, name))
            throw new Exception("Name already exists");
        t.setUserId(userId);
        t.setName(name);
        rep.save(t);
    }

    @Override
    public void updateName(@NotNull String userId, @Nullable String oldName, @Nullable String newName) throws Exception {
        if (oldName == null || oldName.isEmpty())
            throw new Exception("Name is empty");
        if (newName == null || newName.isEmpty())
            throw new Exception("Name is empty");
        T t = findByName(userId, oldName);
        if (rep.existsByUserIdAndName(userId, newName))
            throw new Exception("Name already exists");
        t.setName(newName);
    }

    @Override
    public @NotNull List<T> searchByPart(@NotNull String userId, @Nullable String searchString) throws Exception {
        if (searchString == null || searchString.isEmpty())
            throw new Exception("Request is empty");
        List<T> entities = new ArrayList<>(rep.findAllByUserIdAndNameContaining(userId, searchString));
        entities.addAll(rep.findAllByUserIdAndDescriptionContaining(userId, searchString));
        return entities;
    }

    @Override
    public @NotNull List<T> findAllOrderByStatus(@NotNull String userId) {
        return rep.findAllByUserIdOrderByStatusAsc(userId);
    }

    @Override
    public @NotNull List<T> findAllOrderByName(@NotNull String userId) {
        return rep.findAllByUserIdOrderByNameAsc(userId);
    }

    @Override
    public @NotNull List<T> findAllOrderByStartDate(@NotNull String userId) {
        return rep.findAllByUserIdOrderByStartDateAsc(userId);
    }

    @Override
    public @NotNull List<T> findAllOrderByEndDate(@NotNull String userId) {
        return rep.findAllByUserIdOrderByEndDateAsc(userId);
    }

    @Override
    public @NotNull List<T> findAllOrderByCreationDate(@NotNull String userId) {
        return rep.findAllByUserIdOrderByCreationDateAsc(userId);
    }

    @Override
    public void deleteByName(@NotNull String userId, @Nullable String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("Name is empty");
        rep.deleteByUserIdAndName(userId, name);
    }

    @Override
    public void deleteAllByUserId(@NotNull String userId) {
        rep.deleteAllByUserId(userId);
    }

    @Override
    public @NotNull List<T> findAll() {
        return rep.findAll();
    }

    @Override
    public void saveAll(@NotNull List<T> list) {
        rep.saveAll(list);
    }

    @Override
    public void deleteAll() {
        rep.deleteAll();
    }
}
