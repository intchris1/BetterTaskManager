package org.example.tm.command.data.load;

import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.example.tm.command.CommandInfo.DATA_SER_LOAD_COMMAND;

@Component
public final class DataSerializationLoadCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    private final SessionService sessionService;

    public DataSerializationLoadCommand(ITerminalService terminalService, SessionService sessionService) {
        super(terminalService, true);
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return DATA_SER_LOAD_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_SER_LOAD_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        @NotNull final FileInputStream fis = new FileInputStream("data/data.bin");
        @NotNull final ObjectInputStream ois = new ObjectInputStream(fis);
        @NotNull final ISubjectAreaService subjectAreaService = (ISubjectAreaService) ois.readObject();
        sessionService.signOut();
        subjectAreaService.write();
        ois.close();
        fis.close();
        terminalService.showMessage("DATA LOADED");
        terminalService.showMessage("YOU NEED TO LOGIN AGAIN");

    }
}
