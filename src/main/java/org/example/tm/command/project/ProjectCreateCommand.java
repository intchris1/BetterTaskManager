package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_CREATE_COMMAND;

@Component
public final class ProjectCreateCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final SessionService sessionService;

    public ProjectCreateCommand(IProjectService projectService, SessionService sessionService) {
        super(true);
        this.projectService = projectService;
        this.sessionService = sessionService;
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
        terminalService.showMessage("[PROJECT CREATE]");
        terminalService.showMessage("ENTER PROJECT NAME:");
        String name = terminalService.readLine();
        @Nullable final Project project = new Project();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        project.setUserId(userId);
        project.setName(name);
        if (projectService.save(project) != null) {
            terminalService.showMessage("[OK]");
        } else terminalService.showMessage("[INVALID NAME OR PROJECT ALREADY EXISTS]");
    }
}
