package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.TASK_CLEAR_COMMAND;

@Component
public final class TaskClearCommand extends AbstractCommand {

    private final ITaskService taskService;
    private final SessionService sessionService;

    public TaskClearCommand(ITerminalService terminalService, ITaskService taskService, SessionService sessionService) {
        super(terminalService, true);
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return TASK_CLEAR_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_CLEAR_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        terminalService.showMessage("[TASK CLEAR]");
        taskService.deleteAllByUserId(sessionService.getCurrentSession().getUser().getId());
        terminalService.showMessage("[ALL TASKS WERE REMOVED]");
    }
}
