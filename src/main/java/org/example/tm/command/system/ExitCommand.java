package org.example.tm.command.system;

import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.EXIT_COMMAND;

@Component
public final class ExitCommand extends AbstractCommand {
    public ExitCommand(ITerminalService terminalService) {
        super(terminalService, false);
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
        terminalService.showMessage("GoodBye <3");
    }
}
