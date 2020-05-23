package org.example;

import org.example.tm.command.project.*;
import org.example.tm.command.system.AboutCommand;
import org.example.tm.command.system.ExitCommand;
import org.example.tm.command.system.HelpCommand;
import org.example.tm.command.task.*;
import org.example.tm.command.user.*;
import org.example.tm.context.Bootstrap;
import org.jetbrains.annotations.NotNull;

public class App 
{
    public static void main(String[] args) throws Exception {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        @NotNull final Class[] classes = new Class[] {
                HelpCommand.class,
                ProjectClearCommand.class,
                ProjectCreateCommand.class,
                ProjectListCommand.class,
                ProjectOpenCommand.class,
                ProjectRemoveCommand.class,
                ProjectEditCommand.class,
                ProjectDateStartSortCommand.class,
                TaskClearCommand.class,
                TaskCreateCommand.class,
                TaskListCommand.class,
                TaskOpenCommand.class,
                TaskRemoveCommand.class,
                TaskEditCommand.class,
                TaskDateStartSortCommand.class,
                ExitCommand.class,
                UserCreateCommand.class,
                UserLoginCommand.class,
                UserLogoutCommand.class,
                UserOpenCommand.class,
                UserEditCommand.class,
                UserNewPasswordCommand.class,
                AboutCommand.class
        };
        bootstrap.init(classes);
    }
}
