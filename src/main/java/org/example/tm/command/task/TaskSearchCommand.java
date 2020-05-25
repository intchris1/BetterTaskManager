package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import static org.example.tm.command.CommandInfo.TASK_SEARCH_COMMAND;

public class TaskSearchCommand extends AbstractCommand {

    public TaskSearchCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return TASK_SEARCH_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_SEARCH_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        terminalService.showMessage("ENTER PART OF NAME OR DESCRIPTION:");
        @NotNull final String searchString = terminalService.readLine();
        @NotNull final List<Task> foundTaskList = serviceLocator.getTaskService().
                findByPart(searchString);
        terminalService.showMessage("[LIST OF FOUND TASKS:]");
        terminalService.printList(foundTaskList);
    }
}
