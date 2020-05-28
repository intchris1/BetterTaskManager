package org.example.tm.session;


import org.example.tm.baseApp.service.IUserService;
import org.example.tm.entity.User;
import org.example.tm.util.PasswordHashUtil;
import org.springframework.stereotype.Component;

@Component
public class SessionServiceImpl implements SessionService {
    private final IUserService userService;
    private Session currentSession;

    public SessionServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Session open(String login, String password) {
        final boolean check = checkDataAccess(login, password);
        if (!check) return null;
        final User user = userService.findUserByName(login);
        if (user == null) return null;
        final Session session = new Session();
        session.setUser(user);
        this.currentSession = session;
        return session;
    }

    @Override
    public boolean checkDataAccess(String login, String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        final User user = userService.findUserByName(login);
        if (user == null) return false;
        final String passwordHash = PasswordHashUtil.md5(password);
        if (passwordHash == null || passwordHash.isEmpty()) return false;
        return passwordHash.equals(user.getPassword());
    }

    @Override
    public void signOut() {
        this.currentSession = null;

    }

    @Override
    public Session getCurrentSession() {
        return currentSession;
    }
}
