package org.example.tm.command.user;

import org.example.tm.baseApp.service.IUserService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_NEWPASSWORD_COMMAND;

@Component
public final class UserNewPasswordCommand extends AbstractCommand {

    private final IUserService userService;
    private final SessionService sessionService;

    public UserNewPasswordCommand(IUserService userService, SessionService sessionService) {
        super(true);
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
    public void execute() throws IOException {
        String oldName = sessionService.getCurrentSession().getUser().getName();
        terminalService.showMessage("CURRENT USER NAME: " + oldName);
        terminalService.showMessage("ENTER NEW PASSWORD:");
        String newPassword = terminalService.readLine();
        User user = userService.findOneByName(oldName);
        if (user != null) {
            user.setPassword(newPassword);
            if (userService.editPassword(user) != null)
                terminalService.showMessage("USER PASSWORD WAS EDITED");
        } else {
            terminalService.showMessage("USER NOT FOUND");
        }

    }
}
