package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static org.example.tm.command.CommandInfo.TASK_LIST_COMMAND;

@Component
public final class TaskListCommand extends AbstractCommand {

    private final ITaskService taskService;
    private final SessionService sessionService;

    public TaskListCommand(ITerminalService terminalService, ITaskService taskService, SessionService sessionService) {
        super(terminalService, true);
        this.taskService = taskService;
        this.sessionService = sessionService;
    }


    @Override
    public @NotNull String getName() {
        return TASK_LIST_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return TASK_LIST_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        terminalService.showMessage("[PLEASE ENTER SORT TYPE (creation-date, start-date, end-date, status, name): \n" +
                "]");
        @Nullable String sortType = terminalService.readLine();
        @NotNull final String userId = sessionService.getCurrentSession().getUser().getId();
        @NotNull List<Task> tasks;
        switch (sortType) {
            case "start-date":
                tasks = taskService.findAllOrderByStartDate(userId);
                break;
            case "end-date":
                tasks = taskService.findAllOrderByEndDate(userId);
                break;
            case "status":
                tasks = taskService.findAllOrderByStatus(userId);
                break;
            case "creation-date":
                tasks = taskService.findAllOrderByCreationDate(userId);
                break;
            default:
                sortType = "name";
                tasks = taskService.findAllOrderByName(userId);
        }
        terminalService.showMessage("[TASK LIST SORTED BY " + sortType.toUpperCase() + "]");
        terminalService.printList(tasks);
    }
}
