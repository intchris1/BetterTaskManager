package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.TASK_CLEAR_COMMAND;

@Component
public final class TaskClearCommand extends AbstractCommand {

    private final ITaskService taskService;

    public TaskClearCommand(ITaskService taskService) {
        super(true);
        this.taskService = taskService;
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
        taskService.removeAll();
        terminalService.showMessage("ALL TASKS WERE REMOVED");
    }
}
