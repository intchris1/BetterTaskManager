package org.example.tm.command.system;

import org.example.tm.command.AbstractCommand;
import org.example.tm.command.CommandInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.HELP_COMMAND;

@Component
public final class HelpCommand extends AbstractCommand {
    public HelpCommand() {
        super(false);
    }


    @Override
    public @NotNull String getName() {
        return HELP_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return HELP_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        CommandInfo.printInfo();
    }
}
