package org.example.tm.baseApp.service;

import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

public interface IUserService extends IBaseService<User> {
    User editPassword(@NotNull User user);
}
