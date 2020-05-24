package org.example.tm.command.user;

import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;

import static org.example.tm.command.CommandInfo.USER_OPEN_COMMAND;

public final class UserOpenCommand extends AbstractCommand {

    public UserOpenCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return USER_OPEN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return USER_OPEN_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        terminalService.showMessage(serviceLocator.getSessionService().getCurrentSession().getUser().toString());
    }
}
