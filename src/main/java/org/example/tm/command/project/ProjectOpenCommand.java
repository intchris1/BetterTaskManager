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

import static org.example.tm.command.CommandInfo.PROJECT_OPEN_COMMAND;

@Component
public final class ProjectOpenCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final ITaskService taskService;
    private final SessionService sessionService;

    public ProjectOpenCommand(SessionService sessionService, ITerminalService terminalService, IProjectService projectService, ITaskService taskService) {
        super(terminalService, true);
        this.sessionService = sessionService;
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @Override
    public @NotNull String getName() {
        return PROJECT_OPEN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_OPEN_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER PROJECT NAME TO OPEN: ]");
        @Nullable final String name = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        @NotNull Project project = projectService.findByName(userId, name);
        terminalService.showMessage(project.toString());
        terminalService.printList(taskService.findAllByProjectId(userId, project.getId()));
    }
}
