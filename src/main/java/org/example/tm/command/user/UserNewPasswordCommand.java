package org.example.tm.command.user;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_NEWPASSWORD_COMMAND;

public final class UserNewPasswordCommand extends AbstractCommand {

    public UserNewPasswordCommand() {
        super(true);
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
        String oldName = serviceLocator.getSessionService().getCurrentSession().getUser().getName();
        terminalService.showMessage("CURRENT USER NAME: " + oldName);
        terminalService.showMessage("ENTER NEW PASSWORD:");
        String newPassword = terminalService.readLine();
        User user = serviceLocator.getUserService().findOneByName(oldName);
        if (user != null) {
            user.setPassword(newPassword);
            if (serviceLocator.getUserService().editPassword(user) != null)
                terminalService.showMessage("USER PASSWORD WAS EDITED");
        } else {
            terminalService.showMessage("USER NOT FOUND");
        }

    }
}
