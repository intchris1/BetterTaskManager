package org.example.tm.command.project;

import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    public void execute() {
        serviceLocator.getTerminalService().showMessage("[PROJECT LIST]");
        List<String> namesToPrint = new ArrayList<>();
        for (Project value : serviceLocator.getProjectService().findAll().values()) {
            namesToPrint.add(value.getName());
        }
        super.printList(namesToPrint);
    }
}
