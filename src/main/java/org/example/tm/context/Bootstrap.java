package org.example.tm.context;

import lombok.NoArgsConstructor;
import org.example.tm.baseApp.ServiceLocator;
import org.example.tm.baseApp.repository.IProjectRepository;
import org.example.tm.baseApp.repository.ITaskRepository;
import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.baseApp.service.IUserService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.user.User;
import org.example.tm.repository.ProjectRepositoryImpl;
import org.example.tm.repository.TaskRepositoryImpl;
import org.example.tm.repository.UserRepositoryImpl;
import org.example.tm.service.ProjectServiceImpl;
import org.example.tm.service.TaskServiceImpl;
import org.example.tm.service.TerminalServiceImpl;
import org.example.tm.service.UserServiceImpl;
import org.example.tm.session.SessionService;
import org.example.tm.session.SessionServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepositoryImpl();

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepositoryImpl();

    @NotNull
    private final IUserRepository userRepository = new UserRepositoryImpl();

    @NotNull
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @NotNull
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    private final ITaskService taskService = new TaskServiceImpl(taskRepository);

    private final IProjectService projectService = new ProjectServiceImpl(projectRepository);

    private final IUserService userService = new UserServiceImpl(userRepository);

    private final ITerminalService terminalService = new TerminalServiceImpl(reader);

    private final SessionService sessionService = new SessionServiceImpl(this);

    @NotNull
    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    @NotNull
    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @NotNull
    @Override
    public IUserService getUserService() {
        return userService;
    }

    @NotNull
    @Override
    public ITerminalService getTerminalService() {
        return terminalService;
    }

    @NotNull
    @Override
    public SessionService getSessionService() {
        return sessionService;
    }


    public void init(Class[] classes) throws IOException {
        initializeCommands(classes);
        initializeUsers();
        execute("help");
        while (true) {
            terminalService.showMessage("ENTER COMMAND: ");
            String commandName = terminalService.readLine();
            execute(commandName);
            if (commandName.equals("exit")) break;
        }
    }

    public void initializeCommands(Class[] classes) {
        for (final @Nullable Class aClass : classes) {
            if (aClass == null || !AbstractCommand.class.isAssignableFrom(aClass)) continue;
            try {
                final AbstractCommand instance = (AbstractCommand) aClass.newInstance();
                instance.setServiceLocator(this);
                commands.put(instance.getName(), instance);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void execute(@Nullable final String commandName) throws IOException {
        if (commandName == null || commandName.isEmpty()) {
            return;
        }
        @Nullable final AbstractCommand abstractCommand = commands.get(commandName);
        if (abstractCommand == null) {
            getTerminalService().showMessage("WRONG COMMAND");
            return;
        }
        final boolean secureCheck = !abstractCommand.isSecure() ||
                (abstractCommand.isSecure() && sessionService.getCurrentSession() != null);
        if (secureCheck) {
            abstractCommand.execute();
            return;
        }
        getTerminalService().showMessage("YOU NEED TO AUTHORIZE FIRST");
    }

    public void initializeUsers() {
        User newAdmin = new User();
        newAdmin.setName("admin");
        newAdmin.setPassword("12345");
        userRepository.persist(newAdmin);
        User kate = new User();
        kate.setName("Kate");
        kate.setPassword("123");
        userRepository.persist(kate);
        User mo = new User();
        mo.setName("Mo");
        mo.setPassword("123");
        userRepository.persist(mo);
    }
}
