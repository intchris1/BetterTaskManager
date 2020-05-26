package org.example.tm.command.data.load;

import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.example.tm.command.CommandInfo.DATA_SER_LOAD_COMMAND;

public class DataSerializationLoadCommand extends AbstractCommand {
    public DataSerializationLoadCommand() {
        super(true);
    }

    {
        setRole(RoleType.ADMIN);
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
        serviceLocator.getSessionService().signOut();
        subjectAreaService.write(serviceLocator);
        ois.close();
        fis.close();
        terminalService.showMessage("DATA LOADED");
        terminalService.showMessage("YOU NEED TO LOGIN AGAIN");

    }
}
