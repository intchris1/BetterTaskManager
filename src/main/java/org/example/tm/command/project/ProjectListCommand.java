package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static org.example.tm.command.CommandInfo.PROJECT_LIST_COMMAND;

@Component
public final class ProjectListCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final SessionService sessionService;

    public ProjectListCommand(SessionService sessionService, ITerminalService terminalService, IProjectService projectService) {
        super(terminalService, true);
        this.projectService = projectService;
        this.sessionService = sessionService;
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
        terminalService.showMessage("[PLEASE ENTER SORT TYPE (creation-date, start-date, end-date, status, name): \n" +
                "]");
        @Nullable String sortType = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        @NotNull List<Project> projects;
        switch (sortType) {
            case "start-date":
                projects = projectService.findAllOrderByStartDate(userId);
                break;
            case "end-date":
                projects = projectService.findAllOrderByEndDate(userId);
                break;
            case "status":
                projects = projectService.findAllOrderByStatus(userId);
                break;
            case "creation-date":
                projects = projectService.findAllOrderByCreationDate(userId);
                break;
            default:
                sortType = "name";
                projects = projectService.findAllOrderByName(userId);
        }
        terminalService.showMessage("[PROJECT LIST SORTED BY " + sortType.toUpperCase() + "]");
        terminalService.printList(projects);
    }
}
