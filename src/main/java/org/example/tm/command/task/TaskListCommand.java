package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.entity.Task;
import org.example.tm.util.ComparableEntityComparator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("PLEASE ENTER SORT TYPE: \n" +
                "(creation-date, start-date, end-date, status");
        serviceLocator.getTerminalService().showMessage("[TASK LIST]");
        String sortType = serviceLocator.getTerminalService().readLine();
        List<Project> tasks = serviceLocator.getProjectService().findAll();
        switch (sortType) {
            case "start-date":
                tasks.sort(ComparableEntityComparator.comparatorStartDate);
                break;
            case "end-date":
                tasks.sort(ComparableEntityComparator.comparatorEndDate);
                break;
            case "status":
                tasks.sort(ComparableEntityComparator.comparatorStatus);
                break;
            default:
                sortType = "creation-date";
                tasks.sort(ComparableEntityComparator.comparatorCreationDate);
        }
        serviceLocator.getTerminalService().showMessage("[PROJECT LIST SORTED BY " + sortType.toUpperCase() + "]");
        super.printList(tasks);
    }
}
