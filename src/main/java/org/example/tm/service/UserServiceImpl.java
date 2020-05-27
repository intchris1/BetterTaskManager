package org.example.tm.service;


import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl extends AbstractBaseServiceImpl<User, IUserRepository> implements IUserService {
    public UserServiceImpl(IUserRepository baseRep) {
        super(baseRep);
    }

    @Override
    public User editPassword(@NotNull User user) {
        baseRep.persist(user);
        return user;
    }
}
