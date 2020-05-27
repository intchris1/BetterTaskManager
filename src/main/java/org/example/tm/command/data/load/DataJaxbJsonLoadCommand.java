package org.example.tm.command.data.load;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.example.tm.service.SubjectAreaServiceImpl;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

import static org.example.tm.command.CommandInfo.DATA_JAXB_JSON_LOAD_COMMAND;

@Component
public class DataJaxbJsonLoadCommand extends AbstractCommand {
    {
        setRole(RoleType.ADMIN);
    }

    private final SessionService sessionService;

    public DataJaxbJsonLoadCommand(SessionService sessionService) {
        super(true);
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return DATA_JAXB_JSON_LOAD_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_JAXB_JSON_LOAD_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException, JAXBException {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final File file = new File("data/jaxb.json");
        @NotNull final StreamSource streamSource = new StreamSource(file);
        @NotNull final JAXBContext context = JAXBContext.newInstance(SubjectAreaServiceImpl.class);
        @NotNull final Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        @NotNull final ISubjectAreaService subjectAreaService =
                unmarshaller.unmarshal(streamSource, SubjectAreaServiceImpl.class).getValue();
        sessionService.signOut();
        subjectAreaService.write();
        terminalService.showMessage("DATA LOADED");
        terminalService.showMessage("YOU NEED TO LOGIN AGAIN");
    }
}
