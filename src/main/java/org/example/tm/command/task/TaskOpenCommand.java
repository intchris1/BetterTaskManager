package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.TASK_OPEN_COMMAND;

@Component
public final class TaskOpenCommand extends AbstractCommand {

    private final ITaskService taskService;

    public TaskOpenCommand(ITaskService taskService) {
        super(true);
        this.taskService = taskService;
    }

    @Override
    public @NotNull String getName() {
        return TASK_OPEN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_OPEN_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        terminalService.showMessage("ENTER TASK NAME TO OPEN:");
        String name = terminalService.readLine();
        Task task = taskService.findOneByName(name);
        if (task == null) terminalService.showMessage("TASK NOT FOUND");


        else terminalService.showMessage(task.toString());
    }
}
