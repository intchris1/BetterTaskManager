package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.TASK_REMOVE_COMMAND;

public final class TaskRemoveCommand extends AbstractCommand {
    public TaskRemoveCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return TASK_REMOVE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_REMOVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("ENTER TASK NAME TO DELETE");
        String name = serviceLocator.getTerminalService().readLine();
        Task task = serviceLocator.getTaskService().findOneByName(name);
        if (task == null) serviceLocator.getTerminalService().showMessage("NO SUCH TASK");
        else {
            serviceLocator.getTaskService().remove(task);
            serviceLocator.getTerminalService().showMessage("TASK WAS DELETED");
        }
    }
}
