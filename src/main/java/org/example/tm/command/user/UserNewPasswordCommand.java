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
        serviceLocator.getTerminalService().showMessage("CURRENT USER NAME: " + oldName);
        serviceLocator.getTerminalService().showMessage("ENTER NEW PASSWORD:");
        String newPassword = serviceLocator.getTerminalService().readLine();
        User user = serviceLocator.getUserService().findOneByName(oldName);
        if (user != null) {
            user.setPassword(newPassword);
            if (serviceLocator.getUserService().editPassword(user) != null)
                serviceLocator.getTerminalService().showMessage("USER PASSWORD WAS EDITED");
        } else {
            serviceLocator.getTerminalService().showMessage("USER NOT FOUND");
        }

    }
}
