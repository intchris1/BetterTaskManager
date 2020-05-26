package org.example.tm.command.project;

import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;

import static org.example.tm.command.CommandInfo.PROJECT_CLEAR_COMMAND;

public final class ProjectClearCommand extends AbstractCommand {
    public ProjectClearCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return PROJECT_CLEAR_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_CLEAR_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        terminalService.showMessage("[PROJECT CLEAR]");
        serviceLocator.getProjectService().removeAll();
        serviceLocator.getTaskService().removeAll();
        terminalService.showMessage("[OK]");
    }
}
