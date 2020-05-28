package org.example.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class AbstractCommand {

    protected final ITerminalService terminalService;
    private final boolean isSecure;

    @NotNull
    protected RoleType role = RoleType.USER;

    public AbstractCommand(ITerminalService terminalService, final boolean isSecure) {
        this.terminalService = terminalService;
        this.isSecure = isSecure;
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDescription();

    public abstract void execute() throws Exception;

}
