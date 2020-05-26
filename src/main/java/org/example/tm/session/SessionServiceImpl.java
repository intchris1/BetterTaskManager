package org.example.tm.session;


import org.example.tm.baseApp.ServiceLocator;
import org.example.tm.entity.user.User;
import org.example.tm.util.PasswordHashUtil;

public class SessionServiceImpl implements SessionService {
    private final ServiceLocator serviceLocator;
    private Session currentSession;

    public SessionServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Session open(String login, String password) {
        final boolean check = checkDataAccess(login, password);
        if (!check) return null;
        final User user = serviceLocator.getUserService().findOneByName(login);
        if (user == null) return null;
        final Session session = new Session();
        session.setUser(user);
        this.currentSession = session;
        serviceLocator.getTaskService().setUser(user);
        serviceLocator.getProjectService().setUser(user);
        return session;
    }

    @Override
    public boolean checkDataAccess(String login, String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        final User user = serviceLocator.getUserService().findOneByName(login);
        if (user == null) return false;
        final String passwordHash = PasswordHashUtil.md5(password);
        if (passwordHash == null || passwordHash.isEmpty()) return false;
        return passwordHash.equals(user.getPassword());
    }

    @Override
    public void signOut() {
        this.currentSession = null;
        serviceLocator.getTaskService().setUser(null);
        serviceLocator.getProjectService().setUser(null);

    }

    @Override
    public Session getCurrentSession() {
        return currentSession;
    }
}
