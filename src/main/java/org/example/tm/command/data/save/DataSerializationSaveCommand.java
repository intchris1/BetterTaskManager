package org.example.tm.command.data.save;

import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.example.tm.command.CommandInfo.DATA_SER_SAVE_COMMAND;


@Component
public class DataSerializationSaveCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    @Autowired
    private final BeanFactory beanFactory;

    public DataSerializationSaveCommand(BeanFactory beanFactory) {
        super(true);
        this.beanFactory = beanFactory;
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
        @NotNull final ISubjectAreaService subjectAreaService = beanFactory.getBean(ISubjectAreaService.class);
        subjectAreaService.read();
        @NotNull final File folder = new File("data");
        if (!folder.exists()) folder.mkdir();
        @NotNull final FileOutputStream fos = new FileOutputStream("data/data.bin");
        @NotNull final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(subjectAreaService);
        oos.flush();
        oos.close();
        fos.close();
        terminalService.showMessage("DATA SAVED");
    }

}
