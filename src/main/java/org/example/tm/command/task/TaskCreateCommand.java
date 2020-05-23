package org.example.tm.command.task;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.entity.Task;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.TASK_CREATE_COMMAND;

public final class TaskCreateCommand extends AbstractCommand {
    public TaskCreateCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return TASK_CREATE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_CREATE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("[TASK CREATE]");
        serviceLocator.getTerminalService().showMessage("ENTER TASK NAME:");
        String name = serviceLocator.getTerminalService().readLine();
        if (serviceLocator.getTaskService().findOneByName(name) != null)
            serviceLocator.getTerminalService().showMessage("TASK ALREADY EXISTS");
        else {
            serviceLocator.getTerminalService().showMessage("ENTER PROJECT NAME:");
            String projectName = serviceLocator.getTerminalService().readLine();
            Project project = serviceLocator.getProjectService().findOneByName(projectName);
            if (project == null) serviceLocator.getTerminalService().showMessage("PROJECT NOT FOUND");
            else {
                Task task = new Task();
                task.setName(name);
                task.setUserId(serviceLocator.getSessionService().getCurrentSession().getUser().getId());
                task.setProjectId(project.getId());
                if (serviceLocator.getTaskService().save(task) != null)
                    serviceLocator.getTerminalService().showMessage("TASK WAS CREATED");
            }
        }
    }
}
