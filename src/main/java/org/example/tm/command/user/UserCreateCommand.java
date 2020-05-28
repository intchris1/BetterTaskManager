package org.example.tm.command.user;


import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.USER_CREATE_COMMAND;

@Component
public final class UserCreateCommand extends AbstractCommand {

    private final IUserService userService;

    public UserCreateCommand(ITerminalService terminalService, IUserService userService) {
        super(terminalService, false);
        this.userService = userService;
    }

    @Override
    public @NotNull String getName() {
        return USER_CREATE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return USER_CREATE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        terminalService.showMessage("[USER CREATE]");
        terminalService.showMessage("[ENTER USER NAME: ]");
        @Nullable final String userName = terminalService.readLine();
        terminalService.showMessage("[ENTER PASSWORD: ]");
        @Nullable final String password = terminalService.readLine();
        userService.createNewUser(userName, password);
        terminalService.showMessage("[USER " + userName + " WAS CREATED]");
    }
}
