package org.example.tm.command.data.save;

import org.eclipse.persistence.jaxb.MarshallerProperties;
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

import static org.example.tm.command.CommandInfo.DATA_JAXB_JSON_SAVE_COMMAND;

@Component
public final class DataJaxbJsonSaveCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    private final BeanFactory beanFactory;

    public DataJaxbJsonSaveCommand(ITerminalService terminalService, BeanFactory beanFactory) {
        super(terminalService, true);
        this.beanFactory = beanFactory;
    }

    @Override
    public @NotNull String getName() {
        return DATA_JAXB_JSON_SAVE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_JAXB_JSON_SAVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws JAXBException {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final ISubjectAreaService subjectAreaService = beanFactory.getBean(ISubjectAreaService.class);
        subjectAreaService.read();
        @NotNull final File folder = new File("data");
        if (!folder.exists()) folder.mkdir();
        @NotNull final File file = new File("data/jaxb.json");
        @NotNull final JAXBContext context = JAXBContext.newInstance(SubjectAreaServiceImpl.class);
        @NotNull final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(subjectAreaService, file);
        terminalService.showMessage("DATA SAVED");

    }
}
