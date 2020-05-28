package org.example.tm.command.user;

import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.USER_NEWPASSWORD_COMMAND;

@Component
public final class UserNewPasswordCommand extends AbstractCommand {

    private final IUserService userService;
    private final SessionService sessionService;

    public UserNewPasswordCommand(ITerminalService terminalService, IUserService userService, SessionService sessionService) {
        super(terminalService, true);
        this.userService = userService;
        this.sessionService = sessionService;
    }


    @Override
    public @NotNull String getName() {
        return USER_NEWPASSWORD_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return USER_NEWPASSWORD_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        @NotNull final String oldName = sessionService.getCurrentSession().getUser().getName();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        terminalService.showMessage("[CURRENT USER NAME: " + oldName + "]");
        terminalService.showMessage("[ENTER NEW PASSWORD: ");
        @NotNull final String newPassword = terminalService.readLine();
        userService.editPassword(userId, newPassword);
        terminalService.showMessage("[USER PASSWORD WAS EDITED]");
    }
}
