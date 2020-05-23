package org.example.tm.service;


import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

public class UserServiceImpl extends AbstractBaseServiceImpl<User, IUserRepository> implements IUserService {
    public UserServiceImpl(IUserRepository baseRep) {
        super(baseRep);
    }

    @Override
    public User editPassword(@NotNull User user) {
        if(user.getName() == null) return null;
        baseRep.persist(user);
        return user;
    }
}
