package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.tm.command.CommandInfo.PROJECT_SEARCH_COMMAND;

@Component
public class ProjectSearchCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final SessionService sessionService;

    public ProjectSearchCommand(ITerminalService terminalService, IProjectService projectService, SessionService sessionService) {
        super(terminalService, true);
        this.projectService = projectService;
        this.sessionService = sessionService;
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
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER PART OF NAME OR DESCRIPTION: ]");
        @NotNull final String searchString = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        @NotNull final List<Project> foundProjectList = projectService.searchByPart(userId, searchString);
        terminalService.showMessage("[LIST OF FOUND PROJECTS: ]");
        terminalService.printList(foundProjectList);
    }
}
