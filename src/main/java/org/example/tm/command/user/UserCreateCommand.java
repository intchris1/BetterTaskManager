package org.example.tm.command.user;


import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.example.tm.util.PasswordHashUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_CREATE_COMMAND;

public final class UserCreateCommand extends AbstractCommand {

    public UserCreateCommand() {
        super(false);
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
    public void execute() throws IOException {
        terminalService.showMessage("[USER CREATE]");
        terminalService.showMessage("ENTER USER NAME:");
        String userName = terminalService.readLine();
        if (serviceLocator.getUserService().findOneByName(userName) != null)
            terminalService.showMessage("USER ALREADY EXISTS");
        else {
            terminalService.showMessage("ENTER PASSWORD:");
            String password = terminalService.readLine();
            User user = new User();
            user.setName(userName);
            user.setPassword(PasswordHashUtil.md5(password));
            if (serviceLocator.getUserService().save(user) != null)
                terminalService.showMessage("USER WAS CREATED");
        }
    }
}
