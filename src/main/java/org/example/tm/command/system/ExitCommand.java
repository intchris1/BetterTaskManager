package org.example.tm.command.system;

import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;

import static org.example.tm.command.CommandInfo.EXIT_COMMAND;


public final class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super(false);
    }

    @Override
    public @NotNull String getName() {
        return EXIT_COMMAND.getName();
    }


    @Override
    public @NotNull String getDescription() {
        return EXIT_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        serviceLocator.getTerminalService().showMessage("GoodBye <3");
    }
}
