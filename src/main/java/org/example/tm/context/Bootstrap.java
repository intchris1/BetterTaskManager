package org.example.tm.context;

import org.example.tm.baseApp.IServiceLocator;
import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.command.CommandFactory;
import org.example.tm.entity.user.User;
import org.example.tm.enumeration.RoleType;
import org.example.tm.session.SessionService;
import org.example.tm.util.PasswordHashUtil;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;

@Component
public final class Bootstrap implements IServiceLocator {

    private final CommandFactory factory;
    private final ITerminalService terminalService;
    private final SessionService sessionService;
    private final IUserRepository userRepository;
    public BufferedReader reader;

    private Bootstrap(CommandFactory factory, ITerminalService terminalService, SessionService sessionService, IUserRepository userRepository, BufferedReader reader) {
        this.factory = factory;
        this.terminalService = terminalService;
        this.sessionService = sessionService;
        this.userRepository = userRepository;
        this.reader = reader;
    }

    public void init() throws IOException, ClassNotFoundException, JAXBException {
        initializeUsers();
        execute("help");
        while (true) {
            terminalService.showMessage("ENTER COMMAND: ");
            String commandName = terminalService.readLine();
            execute(commandName);
            if (commandName.equals("exit")) break;
        }
    }


    private void execute(@Nullable final String commandName) throws IOException, ClassNotFoundException, JAXBException {
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
            abstractCommand.execute();
            return;
        }
        terminalService.showMessage("YOU NEED TO AUTHORIZE FIRST");
    }

    public void initializeUsers() {
        User admin = new User();
        admin.setPassword(PasswordHashUtil.md5("admin"));
        admin.setDisplayName(RoleType.ADMIN);
        admin.setName("admin");

        userRepository.persist(admin);

        User kate = new User();
        kate.setPassword(PasswordHashUtil.md5("123"));
        kate.setName("Kate");
        userRepository.persist(kate);

        User mo = new User();
        mo.setPassword(PasswordHashUtil.md5("123"));
        mo.setName("Mo");

        userRepository.persist(mo);
    }
}
