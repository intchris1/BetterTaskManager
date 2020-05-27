package org.example.tm.command.task;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.example.tm.entity.Task;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.TASK_CREATE_COMMAND;

@Component
public final class TaskCreateCommand extends AbstractCommand {

    private final IProjectService projectService;
    private final ITaskService taskService;
    private final SessionService sessionService;

    public TaskCreateCommand(IProjectService projectService, ITaskService taskService, SessionService sessionService) {
        super(true);
        this.projectService = projectService;
        this.taskService = taskService;
        this.sessionService = sessionService;
    }


    @Override
    public @NotNull String getName() {
        return TASK_CREATE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_CREATE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        terminalService.showMessage("[TASK CREATE]");
        terminalService.showMessage("ENTER TASK NAME:");
        String name = terminalService.readLine();
        if (taskService.findOneByName(name) != null)
            terminalService.showMessage("TASK ALREADY EXISTS");
        else {
            terminalService.showMessage("ENTER PROJECT NAME:");
            String projectName = terminalService.readLine();
            Project project = projectService.findOneByName(projectName);
            if (project == null) terminalService.showMessage("PROJECT NOT FOUND");
            else {
                Task task = new Task();
                task.setName(name);
                task.setUserId(sessionService.getCurrentSession().getUser().getId());
                task.setProjectId(project.getId());
                if (taskService.save(task) != null)
                    terminalService.showMessage("TASK WAS CREATED");
            }
        }
    }
}
