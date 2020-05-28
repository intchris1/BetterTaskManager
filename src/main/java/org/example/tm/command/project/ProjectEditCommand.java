package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.PROJECT_EDIT_COMMAND;

@Component
public final class ProjectEditCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final SessionService sessionService;

    public ProjectEditCommand(SessionService sessionService, ITerminalService terminalService, IProjectService projectService) {
        super(terminalService, true);
        this.projectService = projectService;
        this.sessionService = sessionService;
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
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER PROJECT NAME FOR EDITING: ]");
        @Nullable final String oldName = terminalService.readLine();
        terminalService.showMessage("[ENTER NEW PROJECT NAME: ]");
        @Nullable final String newName = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        projectService.updateName(userId, oldName, newName);
        terminalService.showMessage("[PROJECT " + oldName + " WAS EDITED, NEW NAME: " + newName + "]");
    }
}
