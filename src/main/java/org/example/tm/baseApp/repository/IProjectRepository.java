package org.example.tm.baseApp.repository;

import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;

public interface IProjectRepository extends IBaseRepository<Project> {

    void setUser(User user);
}
