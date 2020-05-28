package org.example.tm.context;

import org.example.tm.baseApp.IServiceLocator;
import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.command.CommandFactory;
import org.example.tm.entity.User;
import org.example.tm.enumeration.RoleType;
import org.example.tm.session.SessionService;
import org.example.tm.util.PasswordHashUtil;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public final class Bootstrap implements IServiceLocator {

    private final CommandFactory factory;
    private final ITerminalService terminalService;
    private final SessionService sessionService;
    private final IUserRepository userRepository;

    private Bootstrap(CommandFactory factory, ITerminalService terminalService, SessionService sessionService, IUserRepository userRepository, BufferedReader reader) {
        this.factory = factory;
        this.terminalService = terminalService;
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    public void init() throws IOException {
        initializeUsers();
        execute("help");
        while (true) {
            terminalService.showMessage("ENTER COMMAND: ");
            String commandName = terminalService.readLine();
            execute(commandName);
            if (commandName.equals("exit")) break;
        }
    }


    private void execute(@Nullable final String commandName) {
        if (commandName == null || commandName.isEmpty()) {
            return;
        }
        @Nullable final AbstractCommand abstractCommand = factory.getCommand(commandName);
        if (abstractCommand == null) {
            terminalService.showMessage("WRONG COMMAND");
            return;
        }
        final boolean secureCheck = !abstractCommand.isSecure() ||
                (abstractCommand.isSecure() && (sessionService.getCurrentSession() != null && (sessionService.getCurrentSession().getUser()
                        .getDisplayName() == RoleType.ADMIN || abstractCommand.getRole() == RoleType.USER)));
        if (secureCheck) {
            try {
                abstractCommand.execute();
            } catch (Exception e) {
                terminalService.showMessage(e.getMessage());
            }
            return;
        }
        terminalService.showMessage("YOU NEED TO AUTHORIZE FIRST");
    }

    public void initializeUsers() {
        User admin = new User();
        admin.setPassword(PasswordHashUtil.md5("admin"));
        admin.setDisplayName(RoleType.ADMIN);
        admin.setName("admin");

        userRepository.save(admin);

        User kate = new User();
        kate.setPassword(PasswordHashUtil.md5("123"));
        kate.setName("Kate");
        userRepository.save(kate);

        User mo = new User();
        mo.setPassword(PasswordHashUtil.md5("123"));
        mo.setName("Mo");

        userRepository.save(mo);
    }
}
