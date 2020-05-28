package org.example.tm.baseApp.service;

import org.example.tm.entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IUserService {

    @Nullable User findUserByName(String name);

    void createNewUser(@Nullable String name, @Nullable String password) throws Exception;

    void editPassword(@NotNull String userId, @Nullable String newPassword) throws Exception;

    void editUserName(@NotNull String userId, @Nullable String newName) throws Exception;

    @NotNull List<User> findAll();

    void saveAll(@NotNull List<User> list);

    void deleteAll();

}
