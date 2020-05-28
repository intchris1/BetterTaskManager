package org.example.tm.command.user;

import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.USER_LOGOUT_COMMAND;

@Component
public final class UserLogoutCommand extends AbstractCommand {

    private final SessionService sessionService;

    public UserLogoutCommand(ITerminalService terminalService, SessionService sessionService) {
        super(terminalService, true);
        this.sessionService = sessionService;
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
        sessionService.signOut();
        terminalService.showMessage("[LOGGED OUT]");
    }
}
