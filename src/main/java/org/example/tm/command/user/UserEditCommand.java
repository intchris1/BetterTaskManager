package org.example.tm.command.user;

import org.example.tm.baseApp.service.IUserService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_EDIT_COMMAND;

@Component
public final class UserEditCommand extends AbstractCommand {

    private final IUserService userService;
    private final SessionService sessionService;

    public UserEditCommand(IUserService userService, SessionService sessionService) {
        super(true);
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
    public void execute() throws IOException {
        String oldName = sessionService.getCurrentSession().getUser().getName();
        terminalService.showMessage("CURRENT USER NAME: " + oldName);
        terminalService.showMessage("ENTER NEW NAME:");
        User user = userService.findOneByName(oldName);
        if (user == null) terminalService.showMessage("USER NOT FOUND OR NAME IS INVALID");
        else {
            terminalService.showMessage("ENTER NEW NAME:");
            String newName = terminalService.readLine();
            if (userService.findOneByName(newName) == null) {
                user.setName(newName);
                if (userService.update(user) != null)
                    terminalService.showMessage("USER WAS EDITED");
            } else terminalService.showMessage("USER ALREADY EXISTS");
        }
    }
}
