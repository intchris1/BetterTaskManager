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
        terminalService.showMessage("ENTER PROJECT NAME TO OPEN:");
        String name = terminalService.readLine();
        Project project = serviceLocator.getProjectService().findOneByName(name);
        if (project == null) {
            terminalService.showMessage("NO SUCH PROJECT");
        } else {
            terminalService.showMessage(project.toString());
            terminalService.showMessage("[TASK LIST FOR PROJECT]");
            terminalService.printList(serviceLocator.getTaskService().getByProjectId(project.getId()));
        }
    }
}
