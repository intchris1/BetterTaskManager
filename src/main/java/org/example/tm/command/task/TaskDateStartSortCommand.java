package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

import static org.example.tm.command.CommandInfo.TASK_DATE_START_SORT_COMMAND;

public class TaskDateStartSortCommand extends AbstractCommand {
    public TaskDateStartSortCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return TASK_DATE_START_SORT_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_DATE_START_SORT_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        serviceLocator.getTerminalService().showMessage("[TASK LIST SORTED BY START DATE]");
        List<Task> tasks = serviceLocator.getTaskService().findAll();
        tasks.sort(Comparator.comparing(Task::getStartDate));
        super.printList(tasks);
    }
}
