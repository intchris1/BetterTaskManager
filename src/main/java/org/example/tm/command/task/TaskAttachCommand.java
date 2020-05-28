package org.example.tm.command.task;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.TASK_ATTACH_COMMAND;

@Component
public final class TaskAttachCommand extends AbstractCommand {
    private final ITaskService taskService;
    private final IProjectService projectService;
    private final SessionService sessionService;

    public TaskAttachCommand(ITerminalService terminalService, ITaskService taskService, IProjectService projectService, SessionService sessionService) {
        super(terminalService, true);
        this.taskService = taskService;
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return TASK_ATTACH_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_ATTACH_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER TASK NAME: ]");
        @Nullable final String name = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        terminalService.showMessage("[ENTER PROJECT NAME:]");
        @Nullable final String projectName = terminalService.readLine();
        @NotNull final Project project = projectService.findByName(userId, projectName);
        taskService.attachTask(userId, name, project.getId());
        terminalService.showMessage("[TASK " + name + " WAS ATTACHED TO PROJECT " + projectName + " ]");
    }
}
