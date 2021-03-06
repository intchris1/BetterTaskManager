package org.example.tm.command.data.save;

import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.example.tm.service.SubjectAreaServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

import static org.example.tm.command.CommandInfo.DATA_JAXB_XML_SAVE_COMMAND;


@Component
public final class DataJaxbXmlSaveCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    private final BeanFactory beanFactory;

    public DataJaxbXmlSaveCommand(ITerminalService terminalService, BeanFactory beanFactory) {
        super(terminalService, true);
        this.beanFactory = beanFactory;
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
    public void execute() throws JAXBException {
        @NotNull final ISubjectAreaService subjectAreaService = beanFactory.getBean(ISubjectAreaService.class);
        subjectAreaService.read();
        @NotNull final File folder = new File("data");
        if (!folder.exists()) folder.mkdir();
        @NotNull final File file = new File("data/jaxb.xml");
        @NotNull final JAXBContext context = JAXBContext.newInstance(SubjectAreaServiceImpl.class);
        @NotNull final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(subjectAreaService, file);
        terminalService.showMessage("DATA SAVED");

    }
}
