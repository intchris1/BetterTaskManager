package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.TASK_OPEN_COMMAND;

@Component
public final class TaskOpenCommand extends AbstractCommand {

    private final ITaskService taskService;
    private final SessionService sessionService;

    public TaskOpenCommand(ITerminalService terminalService, ITaskService taskService, SessionService sessionService) {
        super(terminalService, true);
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return TASK_OPEN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_OPEN_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER TASK NAME TO OPEN: ]");
        @Nullable final String name = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        @NotNull final Task task = taskService.findByName(userId, name);
        terminalService.showMessage(task.toString());

    }
}
