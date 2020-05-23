package org.example.tm.repository;

import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class UserRepositoryImpl extends AbstractBaseRepositoryImpl<User> implements IUserRepository {

    @Override
    public @NotNull Map<String, User> findAll() {
        return entities;
    }

    @Override
    public User findOneByName(@NotNull String name) {
        for (Map.Entry<String, User> stringUserEntry : entities.entrySet()) {
            User user = stringUserEntry.getValue();
            if (user.getName().equals(name)) return user;
        }
        return null;
    }

}
