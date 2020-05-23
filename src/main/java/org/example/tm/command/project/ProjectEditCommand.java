package org.example.tm.command.project;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_EDIT_COMMAND;

public final class ProjectEditCommand extends AbstractCommand {

    public ProjectEditCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return PROJECT_EDIT_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_EDIT_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("ENTER PROJECT NAME FOR EDITING:");
        String oldName = serviceLocator.getTerminalService().readLine();
        Project project = serviceLocator.getProjectService().findOneByName(oldName);
        if (project == null) serviceLocator.getTerminalService().showMessage("PROJECT NOT FOUND OR NAME IS INVALID");
        else {
            serviceLocator.getTerminalService().showMessage("ENTER NEW NAME:");
            String newName = serviceLocator.getTerminalService().readLine();
            if (serviceLocator.getProjectService().findOneByName(newName) == null) {
                project.setName(newName);
                if (serviceLocator.getProjectService().update(project) != null)
                    serviceLocator.getTerminalService().showMessage("PROJECT WAS EDITED");
            } else serviceLocator.getTerminalService().showMessage("PROJECT ALREADY EXISTS");
        }
    }
}
