package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.TASK_EDIT_COMMAND;

@Component
public final class TaskEditCommand extends AbstractCommand {

    private final ITaskService taskService;
    private final SessionService sessionService;

    public TaskEditCommand(ITerminalService terminalService, ITaskService taskService, SessionService sessionService) {
        super(terminalService, true);
        this.taskService = taskService;
        this.sessionService = sessionService;
    }


    @Override
    public @NotNull String getName() {
        return TASK_EDIT_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_EDIT_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER TASK NAME FOR EDITING: ]");
        @Nullable final String oldName = terminalService.readLine();
        terminalService.showMessage("[ENTER NEW TASK NAME: ]");
        @Nullable final String newName = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        taskService.updateName(userId, oldName, newName);
        terminalService.showMessage("[TASK " + oldName + " WAS EDITED, NEW NAME: " + newName + "]");
    }
}
