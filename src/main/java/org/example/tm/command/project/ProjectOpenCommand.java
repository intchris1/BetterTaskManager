package org.example.tm.command.project;


import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_OPEN_COMMAND;

public final class ProjectOpenCommand extends AbstractCommand {
    public ProjectOpenCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return PROJECT_OPEN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_OPEN_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("ENTER PROJECT NAME TO OPEN:");
        String name = serviceLocator.getTerminalService().readLine();
        Project project = serviceLocator.getProjectService().findOneByName(name);
        if (project == null) {
            serviceLocator.getTerminalService().showMessage("NO SUCH PROJECT");
        } else {
            serviceLocator.getTerminalService().showMessage(project.toString());
            serviceLocator.getTerminalService().showMessage("[TASK LIST FOR PROJECT]");
            printList(serviceLocator.getTaskService().getByProjectId(project.getId()));
        }
    }
}
