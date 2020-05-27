package org.example.tm.command.user;


import org.example.tm.baseApp.service.IUserService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.example.tm.util.PasswordHashUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_CREATE_COMMAND;

@Component
public final class UserCreateCommand extends AbstractCommand {

    private final IUserService userService;

    public UserCreateCommand(IUserService userService) {
        super(false);
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
    public void execute() throws IOException {
        terminalService.showMessage("[USER CREATE]");
        terminalService.showMessage("ENTER USER NAME:");
        String userName = terminalService.readLine();
        if (userService.findOneByName(userName) != null)
            terminalService.showMessage("USER ALREADY EXISTS");
        else {
            terminalService.showMessage("ENTER PASSWORD:");
            String password = terminalService.readLine();
            User user = new User();
            user.setName(userName);
            user.setPassword(PasswordHashUtil.md5(password));
            if (userService.save(user) != null)
                terminalService.showMessage("USER WAS CREATED");
        }
    }
}
