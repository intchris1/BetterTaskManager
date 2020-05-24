package org.example.tm.command.user;


import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.example.tm.enumeration.RoleType;
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
        terminalService.showMessage("[USER CREATE]");
        terminalService.showMessage("ENTER USER NAME:");
        String name = terminalService.readLine();
        if (serviceLocator.getUserService().findOneByName(name) != null)
            terminalService.showMessage("USER ALREADY EXISTS");
        else {
            terminalService.showMessage("ENTER USER NAME:");
            String userName = terminalService.readLine();
            terminalService.showMessage("ENTER PASSWORD:");
            String password = terminalService.readLine();
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            if (userName.equals("admin") && password.equals("12345")) user.setDisplayName(RoleType.ADMIN);
            else user.setDisplayName(RoleType.USER);
            if (serviceLocator.getUserService().save(user) != null)
                terminalService.showMessage("USER WAS CREATED");
        }
    }
}
