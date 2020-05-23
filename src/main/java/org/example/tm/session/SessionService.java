package org.example.tm.session;


public interface SessionService {
    Session open(final String login, final String password);

    boolean checkDataAccess(final String login, final String password);

    void signOut();

    Session getCurrentSession();
}
