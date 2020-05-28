package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.tm.command.CommandInfo.TASK_SEARCH_COMMAND;


@Component
public class TaskSearchCommand extends AbstractCommand {

    private final ITaskService taskService;
    private final SessionService sessionService;

    public TaskSearchCommand(ITerminalService terminalService, ITaskService taskService, SessionService sessionService) {
        super(terminalService, true);
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return TASK_SEARCH_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_SEARCH_COMMAND.getDescription();
    }

    @Override
    public void execute() throws Exception {
        terminalService.showMessage("[ENTER PART OF NAME OR DESCRIPTION: ]");
        @NotNull final String searchString = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        @NotNull final List<Task> foundTaskList = taskService.searchByPart(userId, searchString);
        terminalService.showMessage("[LIST OF FOUND TASKS: ]");
        terminalService.printList(foundTaskList);
    }
}
