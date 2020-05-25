package org.example.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.example.tm.baseApp.ServiceLocator;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Getter
@Setter
public abstract class AbstractCommand {
    protected ServiceLocator serviceLocator;
    private final boolean isSecure;
    protected ITerminalService terminalService;

    @NotNull
    protected RoleType role = RoleType.USER;

    public AbstractCommand(final boolean isSecure) {
        this.isSecure = isSecure;
    }

    public void setServiceLocator(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
        this.terminalService = serviceLocator.getTerminalService();
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDescription();

    public abstract void execute() throws IOException, ClassNotFoundException, JAXBException;

}
