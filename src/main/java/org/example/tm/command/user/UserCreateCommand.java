package org.example.tm.command.user;


import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.RoleType;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_CREATE_COMMAND;

public final class UserCreateCommand extends AbstractCommand {

    public UserCreateCommand() {
        super(true);
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
        serviceLocator.getTerminalService().showMessage("[USER CREATE]");
        serviceLocator.getTerminalService().showMessage("ENTER USER NAME:");
        String name = serviceLocator.getTerminalService().readLine();
        if (serviceLocator.getUserService().findOneByName(name) != null) serviceLocator.getTerminalService().showMessage("USER ALREADY EXISTS");
        else {
            serviceLocator.getTerminalService().showMessage("ENTER USER NAME:");
            String userName = serviceLocator.getTerminalService().readLine();
            serviceLocator.getTerminalService().showMessage("ENTER PASSWORD:");
            String password = serviceLocator.getTerminalService().readLine();
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            if (userName.equals("admin") && password.equals("12345")) user.setDisplayName(RoleType.ADMIN);
            else user.setDisplayName(RoleType.USER);
            if (serviceLocator.getUserService().save(user) != null) serviceLocator.getTerminalService().showMessage("USER WAS CREATED");
        }
    }
}
