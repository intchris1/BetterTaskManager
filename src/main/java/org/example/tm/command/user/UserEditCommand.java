package org.example.tm.command.user;

import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.USER_EDIT_COMMAND;

@Component
public final class UserEditCommand extends AbstractCommand {

    private final IUserService userService;
    private final SessionService sessionService;

    public UserEditCommand(ITerminalService terminalService, IUserService userService, SessionService sessionService) {
        super(terminalService, true);
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return USER_EDIT_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return USER_EDIT_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        @Nullable final String oldName = sessionService.getCurrentSession().getUser().getName();
        terminalService.showMessage("[CURRENT USER NAME: ]" + oldName);
        terminalService.showMessage("[ENTER NEW NAME: ]");
        @Nullable final String newName = terminalService.readLine();
        userService.editUserName(oldName, newName);
        terminalService.showMessage("[USER NAME WAS EDITED, NEW USER NAME: " + newName);
    }
}
