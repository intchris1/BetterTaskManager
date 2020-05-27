package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_REMOVE_COMMAND;

@Component
public final class ProjectRemoveCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final ITaskService taskService;

    public ProjectRemoveCommand(IProjectService projectService, ITaskService taskService) {
        super(true);
        this.projectService = projectService;
        this.taskService = taskService;
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
        terminalService.showMessage("ENTER PROJECT NAME TO DELETE");
        String name = terminalService.readLine();
        Project project = projectService.findOneByName(name);
        if (project == null) terminalService.showMessage("NO SUCH PROJECT");
        else {
            projectService.remove(project);
            taskService.removeByProjectId(project.getId());
            terminalService.showMessage("PROJECT WAS DELETED");
        }
    }
}
