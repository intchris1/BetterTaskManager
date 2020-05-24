package org.example.tm.command.user;

import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;

import static org.example.tm.command.CommandInfo.USER_LOGOUT_COMMAND;

public final class UserLogoutCommand extends AbstractCommand {

    public UserLogoutCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return USER_LOGOUT_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return USER_LOGOUT_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        serviceLocator.getSessionService().signOut();
        terminalService.showMessage("LOGGED OUT");
    }
}
