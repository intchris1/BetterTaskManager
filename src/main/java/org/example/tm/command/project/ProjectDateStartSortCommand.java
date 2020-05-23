package org.example.tm.command.project;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

import static org.example.tm.command.CommandInfo.PROJECT_DATE_START_SORT_COMMAND;

public class ProjectDateStartSortCommand extends AbstractCommand {
    public ProjectDateStartSortCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return PROJECT_DATE_START_SORT_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_DATE_START_SORT_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        serviceLocator.getTerminalService().showMessage("[PROJECT LIST SORTED BY START DATE]");
        List<Project> projects = serviceLocator.getProjectService().findAll();
        projects.sort(Comparator.comparing(Project::getStartDate));
        super.printList(projects);
    }
}
