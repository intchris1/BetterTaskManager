package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.TASK_EDIT_COMMAND;

public final class TaskEditCommand extends AbstractCommand {
    public TaskEditCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return TASK_EDIT_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_EDIT_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("ENTER TASK NAME FOR EDITING:");
        String oldName = serviceLocator.getTerminalService().readLine();
        Task task = serviceLocator.getTaskService().findOneByName(oldName);
        if (task == null) serviceLocator.getTerminalService().showMessage("TASK NOT FOUND OR NAME IS INVALID");
        else {
            serviceLocator.getTerminalService().showMessage("ENTER NEW NAME:");
            String newName = serviceLocator.getTerminalService().readLine();
            task.setName(newName);
            if (serviceLocator.getTaskService().update(task) != null)
                serviceLocator.getTerminalService().showMessage("TASK WAS EDITED");
            else serviceLocator.getTerminalService().showMessage("TASK ALREADY EXISTS");
        }
    }
}
