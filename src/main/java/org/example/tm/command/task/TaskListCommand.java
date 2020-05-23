package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.example.tm.command.CommandInfo.TASK_LIST_COMMAND;

public final class TaskListCommand extends AbstractCommand {
    public TaskListCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return TASK_LIST_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_LIST_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        serviceLocator.getTerminalService().showMessage("[TASK LIST]");
        List<String> namesToPrint = new ArrayList<>();
        for (Task value : serviceLocator.getTaskService().findAll().values()) {
            namesToPrint.add(value.getName());
        }
        printList(namesToPrint);
    }
}
