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
        terminalService.showMessage("ENTER TASK NAME FOR EDITING:");
        String oldName = terminalService.readLine();
        Task task = serviceLocator.getTaskService().findOneByName(oldName);
        if (task == null) terminalService.showMessage("TASK NOT FOUND OR NAME IS INVALID");
        else {
            terminalService.showMessage("ENTER NEW NAME:");
            String newName = terminalService.readLine();
            task.setName(newName);
            if (serviceLocator.getTaskService().update(task) != null)
                terminalService.showMessage("TASK WAS EDITED");
            else terminalService.showMessage("TASK ALREADY EXISTS");
        }
    }
}
