package org.example.tm.command.data.load;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.example.tm.service.SubjectAreaServiceImpl;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.example.tm.command.CommandInfo.DATA_FASTERXML_JSON_LOAD_COMMAND;


public class DataFasterxmlJsonLoadCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    public DataFasterxmlJsonLoadCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return DATA_FASTERXML_JSON_LOAD_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_FASTERXML_JSON_LOAD_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException, JAXBException {
        @NotNull final File file = new File("data/fasterxml.json");
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        @NotNull final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        @NotNull final ISubjectAreaService subjectAreaService = objectMapper.readValue(file, SubjectAreaServiceImpl.class);
        serviceLocator.getSessionService().signOut();
        subjectAreaService.write(serviceLocator);
        terminalService.showMessage("DATA LOADED");
        terminalService.showMessage("YOU NEED TO LOGIN AGAIN");

    }
}