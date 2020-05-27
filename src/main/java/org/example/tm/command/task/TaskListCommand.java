package org.example.tm.command.task;

import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.entity.Task;
import org.example.tm.util.ComparableEntityComparator;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static org.example.tm.command.CommandInfo.TASK_LIST_COMMAND;

@Component
public final class TaskListCommand extends AbstractCommand {

    private final ITaskService taskService;

    public TaskListCommand(ITaskService taskService) {
        super(true);
        this.taskService = taskService;
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
        terminalService.showMessage("PLEASE ENTER SORT TYPE: \n" +
                "(creation-date, start-date, end-date, status)");
        terminalService.showMessage("[TASK LIST]");
        String sortType = terminalService.readLine();
        List<Task> tasks = taskService.findByUserId();
        switch (sortType) {
            case "start-date":
                tasks.sort(ComparableEntityComparator.comparatorStartDate);
                break;
            case "end-date":
                tasks.sort(ComparableEntityComparator.comparatorEndDate);
                break;
            case "status":
                tasks.sort(ComparableEntityComparator.comparatorStatus);
                break;
            default:
                sortType = "creation-date";
                tasks.sort(ComparableEntityComparator.comparatorCreationDate);
        }
        terminalService.showMessage("[TASK LIST SORTED BY " + sortType.toUpperCase() + "]");
        terminalService.printList(tasks);
    }
}
