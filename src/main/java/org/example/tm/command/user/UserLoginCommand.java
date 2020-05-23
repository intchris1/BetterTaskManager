package org.example.tm.command.user;

import org.example.tm.command.AbstractCommand;
import org.example.tm.session.Session;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_LOGIN_COMMAND;


public final class UserLoginCommand extends AbstractCommand {

    public UserLoginCommand() {
        super(false);
    }


    @Override
    public @NotNull String getName() {
        return USER_LOGIN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return USER_LOGIN_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("ENTER USER NAME:");
        String login = serviceLocator.getTerminalService().readLine();
        serviceLocator.getTerminalService().showMessage("ENTER PASSWORD:");
        String password = serviceLocator.getTerminalService().readLine();
        Session session = serviceLocator.getSessionService().open(login, password);
        if (session != null) {
            serviceLocator.getTerminalService().showMessage("SUCCESSFULLY LOGGED IN");
        } else serviceLocator.getTerminalService().showMessage("USER NOT FOUND OR PASSWORD IS INVALID");
    }
}
