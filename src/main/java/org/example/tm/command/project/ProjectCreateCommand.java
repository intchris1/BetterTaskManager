package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_CREATE_COMMAND;

public final class ProjectCreateCommand extends AbstractCommand {
    public ProjectCreateCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return PROJECT_CREATE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_CREATE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("[PROJECT CREATE]");
        serviceLocator.getTerminalService().showMessage("ENTER PROJECT NAME:");
        String name = serviceLocator.getTerminalService().readLine();
        @Nullable final Project project = new Project();
        @NotNull final String userId = serviceLocator.getSessionService().getCurrentSession().getUser().getId();
        project.setUserId(userId);
        project.setName(name);
        if (serviceLocator.getProjectService().save(project) != null) {
            serviceLocator.getTerminalService().showMessage("[OK]");
        } else serviceLocator.getTerminalService().showMessage("[INVALID NAME OR PROJECT ALREADY EXISTS]");
    }
}