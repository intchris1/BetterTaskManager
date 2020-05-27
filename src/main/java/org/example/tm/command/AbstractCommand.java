package org.example.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Getter
@Setter
public abstract class AbstractCommand {
    @Autowired
    protected ITerminalService terminalService;
    private final boolean isSecure;
    @NotNull
    protected RoleType role = RoleType.USER;

    public AbstractCommand(final boolean isSecure) {
        this.isSecure = isSecure;
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDescription();

    public abstract void execute() throws IOException, ClassNotFoundException, JAXBException;

}
