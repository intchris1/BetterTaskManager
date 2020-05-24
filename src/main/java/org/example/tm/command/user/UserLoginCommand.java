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
        terminalService.showMessage("ENTER USER NAME:");
        String login = terminalService.readLine();
        terminalService.showMessage("ENTER PASSWORD:");
        String password = terminalService.readLine();
        Session session = serviceLocator.getSessionService().open(login, password);
        if (session != null) {
            terminalService.showMessage("SUCCESSFULLY LOGGED IN");
        } else terminalService.showMessage("USER NOT FOUND OR PASSWORD IS INVALID");
    }
}
