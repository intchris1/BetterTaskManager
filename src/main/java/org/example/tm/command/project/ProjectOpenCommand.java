package org.example.tm.command.project;


import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_OPEN_COMMAND;

@Component
public final class ProjectOpenCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final ITaskService taskService;

    public ProjectOpenCommand(IProjectService projectService, ITaskService taskService) {
        super(true);
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @Override
    public @NotNull String getName() {
        return PROJECT_OPEN_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return PROJECT_OPEN_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        terminalService.showMessage("ENTER PROJECT NAME TO OPEN:");
        String name = terminalService.readLine();
        Project project = projectService.findOneByName(name);
        if (project == null) {
            terminalService.showMessage("NO SUCH PROJECT");
        } else {
            terminalService.showMessage(project.toString());
            terminalService.showMessage("[TASK LIST FOR PROJECT]");
            terminalService.printList(taskService.getByProjectId(project.getId()));
        }
    }
}
