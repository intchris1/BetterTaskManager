package org.example.tm.command.project;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Project;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.example.tm.command.CommandInfo.PROJECT_EDIT_COMMAND;

@Component
public final class ProjectEditCommand extends AbstractCommand {

    private final IProjectService projectService;


    public ProjectEditCommand(IProjectService projectService) {
        super(true);
        this.projectService = projectService;
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
    public void execute() throws IOException {
        terminalService.showMessage("ENTER PROJECT NAME FOR EDITING:");
        String oldName = terminalService.readLine();
        Project project = projectService.findOneByName(oldName);
        if (project == null) terminalService.showMessage("PROJECT NOT FOUND OR NAME IS INVALID");
        else {
            terminalService.showMessage("ENTER NEW NAME:");
            String newName = terminalService.readLine();
            if (projectService.findOneByName(newName) == null) {
                project.setName(newName);
                if (projectService.update(project) != null)
                    terminalService.showMessage("PROJECT WAS EDITED");
            } else terminalService.showMessage("PROJECT ALREADY EXISTS");
        }
    }
}
