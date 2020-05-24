package org.example.tm.command.user;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_EDIT_COMMAND;

public final class UserEditCommand extends AbstractCommand {

    public UserEditCommand() {
        super(true);
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
        String oldName = serviceLocator.getSessionService().getCurrentSession().getUser().getName();
        terminalService.showMessage("CURRENT USER NAME: " + oldName);
        terminalService.showMessage("ENTER NEW NAME:");
        User user = serviceLocator.getUserService().findOneByName(oldName);
        if (user == null) terminalService.showMessage("USER NOT FOUND OR NAME IS INVALID");
        else {
            terminalService.showMessage("ENTER NEW NAME:");
            String newName = terminalService.readLine();
            if (serviceLocator.getUserService().findOneByName(newName) == null) {
                user.setName(newName);
                if (serviceLocator.getUserService().update(user) != null)
                    terminalService.showMessage("USER WAS EDITED");
            } else terminalService.showMessage("USER ALREADY EXISTS");
        }
    }
}
