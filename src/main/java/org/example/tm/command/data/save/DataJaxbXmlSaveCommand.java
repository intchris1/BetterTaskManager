package org.example.tm.command.data.save;

import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.example.tm.service.SubjectAreaServiceImpl;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

import static org.example.tm.command.CommandInfo.DATA_JAXB_XML_SAVE_COMMAND;

public class DataJaxbXmlSaveCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    public DataJaxbXmlSaveCommand() {
        super(true);
    }

    @Override
    public @NotNull String getName() {
        return DATA_JAXB_XML_SAVE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_JAXB_XML_SAVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException, JAXBException {
        @NotNull final ISubjectAreaService subjectAreaService = new SubjectAreaServiceImpl();
        subjectAreaService.read(serviceLocator);
        @NotNull final File folder = new File("data");
        if (!folder.exists()) folder.mkdir();
        @NotNull final File file = new File("data/jaxb.xml");
        @NotNull final JAXBContext context = JAXBContext.newInstance(SubjectAreaServiceImpl.class);
        @NotNull final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(subjectAreaService,file);
        System.out.println("Data saved.");

    }
}
