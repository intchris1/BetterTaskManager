package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.PROJECT_REMOVE_COMMAND;

@Component
public final class ProjectRemoveCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final ITaskService taskService;
    private final SessionService sessionService;

    public ProjectRemoveCommand(ITerminalService terminalService, IProjectService projectService, ITaskService taskService, SessionService sessionService) {
        super(terminalService, true);
        this.projectService = projectService;
        this.taskService = taskService;
        this.sessionService = sessionService;
    }


    @Override
    public @NotNull String getName() {
        return PROJECT_REMOVE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_REMOVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER PROJECT NAME TO DELETE: ]");
        @Nullable final String name = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        Project project = projectService.findByName(userId, name);
        projectService.deleteAllByUserId(userId);
        taskService.deleteAllByProjectId(userId, project.getId());
        terminalService.showMessage("[PROJECT WAS DELETED, ALL TASKS FOR PROJECT WERE DELETED]");
    }
}
