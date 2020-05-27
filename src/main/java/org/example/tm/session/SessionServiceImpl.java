package org.example.tm.session;


import org.example.tm.baseApp.IServiceLocator;
import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.entity.user.User;
import org.example.tm.util.PasswordHashUtil;
import org.springframework.stereotype.Component;

@Component
public class SessionServiceImpl implements SessionService {
    private final ITaskService taskService;
    private final IUserService userService;
    private final IProjectService projectService;
    private Session currentSession;

    public SessionServiceImpl(ITaskService taskService, IUserService userService, IProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public Session open(String login, String password) {
        final boolean check = checkDataAccess(login, password);
        if (!check) return null;
        final User user = userService.findOneByName(login);
        if (user == null) return null;
        final Session session = new Session();
        session.setUser(user);
        this.currentSession = session;
        taskService.setUser(user);
        projectService.setUser(user);
        return session;
    }

    @Override
    public boolean checkDataAccess(String login, String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        final User user = userService.findOneByName(login);
        if (user == null) return false;
        final String passwordHash = PasswordHashUtil.md5(password);
        if (passwordHash == null || passwordHash.isEmpty()) return false;
        return passwordHash.equals(user.getPassword());
    }

    @Override
    public void signOut() {
        this.currentSession = null;
        taskService.setUser(null);
        projectService.setUser(null);

    }

    @Override
    public Session getCurrentSession() {
        return currentSession;
    }
}
