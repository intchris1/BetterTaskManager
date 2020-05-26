package org.example.tm.command.project;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import static org.example.tm.command.CommandInfo.PROJECT_SEARCH_COMMAND;

public class ProjectSearchCommand extends AbstractCommand {

    public ProjectSearchCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return PROJECT_SEARCH_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_SEARCH_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        terminalService.showMessage("ENTER PART OF NAME OR DESCRIPTION:");
        @NotNull final String searchString = terminalService.readLine();
        @NotNull final List<Project> foundProjectList = serviceLocator.getProjectService().
                findByPart(searchString);
        terminalService.showMessage("[LIST OF FOUND PROJECTS:]");
        terminalService.printList(foundProjectList);
    }
}
