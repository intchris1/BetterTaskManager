package org.example.tm.command.project;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_REMOVE_COMMAND;

public final class ProjectRemoveCommand extends AbstractCommand {

    public ProjectRemoveCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return PROJECT_REMOVE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_REMOVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        serviceLocator.getTerminalService().showMessage("ENTER PROJECT NAME TO DELETE");
        String name = serviceLocator.getTerminalService().readLine();
        Project project = serviceLocator.getProjectService().findOneByName(name);
        if (project == null) serviceLocator.getTerminalService().showMessage("NO SUCH PROJECT");
        else {
            serviceLocator.getProjectService().remove(project);
            serviceLocator.getTaskService().removeByProjectId(project.getId());
            serviceLocator.getTerminalService().showMessage("PROJECT WAS DELETED");
        }
    }
}
