package org.example.tm.command.user;

import org.example.tm.command.AbstractCommand;
import org.example.tm.session.Session;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.USER_LOGIN_COMMAND;

@Component
public final class UserLoginCommand extends AbstractCommand {

    private final SessionService sessionService;

    public UserLoginCommand(SessionService sessionService) {
        super(false);
        this.sessionService = sessionService;
    }


    @Override
    public @NotNull String getName() {
        return USER_LOGIN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return USER_LOGIN_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        if (sessionService.getCurrentSession() != null) {
            terminalService.showMessage("YOU NEED TO SIGN OUT FIRST");
            return;
        }
        terminalService.showMessage("ENTER USER NAME:");
        String login = terminalService.readLine();
        terminalService.showMessage("ENTER PASSWORD:");
        String password = terminalService.readLine();
        Session session = sessionService.open(login, password);
        if (session != null) {
            terminalService.showMessage("SUCCESSFULLY LOGGED IN");
        } else terminalService.showMessage("USER NOT FOUND OR PASSWORD IS INVALID");
    }
}
