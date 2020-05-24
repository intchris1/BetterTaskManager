package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;

import static org.example.tm.command.CommandInfo.TASK_CLEAR_COMMAND;

public final class TaskClearCommand extends AbstractCommand {
    public TaskClearCommand() {
        super(true);
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
        serviceLocator.getTaskService().removeAll();
        terminalService.showMessage("ALL TASKS WERE REMOVED");
    }
}
