package org.example.tm.command.data.save;

import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.example.tm.command.CommandInfo.DATA_SER_SAVE_COMMAND;

public class DataSerializationSaveCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    public DataSerializationSaveCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return DATA_SER_SAVE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_SER_SAVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException {
        @NotNull final ISubjectAreaService subjectAreaService = serviceLocator.getSubjectAreaService();
        subjectAreaService.read(serviceLocator);
        @NotNull final File folder = new File("data");
        if (!folder.exists()) folder.mkdir();
        @NotNull final FileOutputStream fos = new FileOutputStream("data/data.bin");
        @NotNull final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(subjectAreaService);
        oos.close();
        fos.close();
        terminalService.showMessage("DATA SAVED");
    }

}
