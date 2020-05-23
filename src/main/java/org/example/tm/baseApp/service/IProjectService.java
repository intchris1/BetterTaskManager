package org.example.tm.baseApp.service;

import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;

public interface IProjectService extends IBaseService<Project> {

    void setUser(User user);

}
