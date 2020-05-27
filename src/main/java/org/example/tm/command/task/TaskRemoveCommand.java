package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.TASK_REMOVE_COMMAND;


@Component
public final class TaskRemoveCommand extends AbstractCommand {

    private final ITaskService taskService;

    public TaskRemoveCommand(ITaskService taskService) {
        super(true);
        this.taskService = taskService;
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
        terminalService.showMessage("ENTER TASK NAME TO DELETE");
        String name = terminalService.readLine();
        Task task = taskService.findOneByName(name);
        if (task == null) terminalService.showMessage("NO SUCH TASK");
        else {
            taskService.remove(task);
            terminalService.showMessage("TASK WAS DELETED");
        }
    }
}
