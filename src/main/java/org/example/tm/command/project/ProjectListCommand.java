package org.example.tm.command.project;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.util.ComparableEntityComparator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.example.tm.command.CommandInfo.PROJECT_LIST_COMMAND;

public final class ProjectListCommand extends AbstractCommand {
    public ProjectListCommand() {
        super(true);
    }


    @Override
    public @NotNull String getName() {
        return PROJECT_LIST_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_LIST_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        terminalService.showMessage("PLEASE ENTER SORT TYPE: \n" +
                "(creation-date, start-date, end-date, status");
        String sortType = terminalService.readLine();
        List<Project> projects = serviceLocator.getProjectService().findAll();
        switch (sortType) {
            case "start-date":
                projects.sort(ComparableEntityComparator.comparatorStartDate);
                break;
            case "end-date":
                projects.sort(ComparableEntityComparator.comparatorEndDate);
                break;
            case "status":
                projects.sort(ComparableEntityComparator.comparatorStatus);
                break;
            default:
                projects.sort(ComparableEntityComparator.comparatorCreationDate);
        }
        terminalService.showMessage("[PROJECT LIST SORTED BY " + sortType.toUpperCase() + "]");
        terminalService.printList(projects);
    }
}
