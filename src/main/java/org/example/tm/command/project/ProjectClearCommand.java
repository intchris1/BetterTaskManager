package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.PROJECT_CLEAR_COMMAND;

@Component
public final class ProjectClearCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final ITaskService taskService;
    private final SessionService sessionService;

    public ProjectClearCommand(SessionService sessionService, ITerminalService terminalService, IProjectService projectService, ITaskService taskService) {
        super(terminalService, true);
        this.projectService = projectService;
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return PROJECT_CLEAR_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_CLEAR_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        terminalService.showMessage("[PROJECT CLEAR]");
        projectService.deleteAllByUserId(sessionService.getCurrentSession().getUser().getId());
        taskService.deleteAllByUserId(sessionService.getCurrentSession().getUser().getId());
        terminalService.showMessage("[ALL PROJECTS ARE DELETED]");
    }
}
