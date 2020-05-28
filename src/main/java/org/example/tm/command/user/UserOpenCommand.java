package org.example.tm.command.user;

import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.USER_OPEN_COMMAND;

@Component
public final class UserOpenCommand extends AbstractCommand {

    private final SessionService sessionService;

    public UserOpenCommand(ITerminalService terminalService, SessionService sessionService) {
        super(terminalService, true);
        this.sessionService = sessionService;
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
        terminalService.showMessage(sessionService.getCurrentSession().getUser().toString());
    }
}
