package org.example.tm.repository;

import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserRepositoryImpl extends AbstractBaseRepositoryImpl<User> implements IUserRepository {

    @Override
    public @NotNull List<User> findAll() {
        return new ArrayList<>(entities.values());
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
