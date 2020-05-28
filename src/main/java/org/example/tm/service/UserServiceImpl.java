package org.example.tm.service;


import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.entity.User;
import org.example.tm.util.PasswordHashUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository rep;

    public UserServiceImpl(IUserRepository userRepository) {
        this.rep = userRepository;
    }

    @Override
    public void createNewUser(@Nullable String name, @Nullable String password) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("Name is empty");
        if (password == null || password.isEmpty())
            throw new Exception("Password is empty");
        if (rep.existsByName(name))
            throw new Exception("User already exists");
        User user = new User();
        user.setName(name);
        user.setPassword(PasswordHashUtil.md5(password));
        rep.save(user);
    }

    @Override
    public @NotNull User findUserByName(String name) {
        return rep.findByName(name);
    }

    @Override
    public void editPassword(@NotNull String userId, @Nullable String newPassword) throws Exception {
        if (newPassword == null || newPassword.isEmpty())
            throw new Exception("Password cannot be empty");
        User user = rep.findById(userId).orElse(null);
        if (user == null)
            throw new Exception("User cannot be found");
        user.setPassword(PasswordHashUtil.md5(newPassword));
    }

    @Override
    public void editUserName(@NotNull String userId, @Nullable String newName) throws Exception {
        if (newName == null || newName.isEmpty())
            throw new Exception("Name is empty");
        User user = rep.findById(userId).orElse(null);
        if (user == null)
            throw new Exception("User not found");
        if (rep.findByName(newName) != null)
            throw new Exception("User already exists");
        user.setName(newName);
    }

    @Override
    public @NotNull List<User> findAll() {
        return rep.findAll();
    }

    @Override
    public void saveAll(@NotNull List<User> list) {
        rep.saveAll(list);
    }

    @Override
    public void deleteAll() {
        rep.deleteAll();
    }


}
