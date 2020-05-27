package org.example.tm.command.data.load;

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
import java.io.File;
import java.io.IOException;

import static org.example.tm.command.CommandInfo.DATA_JAXB_XML_LOAD_COMMAND;
import static org.example.tm.command.CommandInfo.DATA_JAXB_XML_SAVE_COMMAND;

@Component
public class DataJaxbXmlLoadCommand extends AbstractCommand {
    {
        setRole(RoleType.ADMIN);
    }

    private final SessionService sessionService;

    public DataJaxbXmlLoadCommand(SessionService sessionService) {
        super(true);
        this.sessionService = sessionService;
    }

    @Override
    public @NotNull String getName() {
        return DATA_JAXB_XML_LOAD_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_JAXB_XML_SAVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException, JAXBException {
        @NotNull final File file = new File("data/jaxb.xml");
        @NotNull final JAXBContext context = JAXBContext.newInstance(SubjectAreaServiceImpl.class);
        @NotNull final Unmarshaller unmarshaller = context.createUnmarshaller();
        @NotNull final ISubjectAreaService subjectAreaService = (ISubjectAreaService) unmarshaller.unmarshal(file);
        sessionService.signOut();
        subjectAreaService.write();
        terminalService.showMessage("DATA LOADED");
        terminalService.showMessage("YOU NEED TO LOGIN AGAIN");
    }
}
