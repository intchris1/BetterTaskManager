package org.example.tm.command.data.save;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.example.tm.command.CommandInfo.DATA_FASTERXML_XML_SAVE_COMMAND;



@Component
public class DataFasterxmlXmlSaveCommand extends AbstractCommand {

    {
        setRole(RoleType.ADMIN);
    }

    @Autowired
    private final BeanFactory beanFactory;

    public DataFasterxmlXmlSaveCommand(BeanFactory beanFactory) {
        super(true);
        this.beanFactory = beanFactory;
    }

    @Override
    public @NotNull String getName() {
        return DATA_FASTERXML_XML_SAVE_COMMAND.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DATA_FASTERXML_XML_SAVE_COMMAND.getDescription();
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException, JAXBException {
        @NotNull final ISubjectAreaService subjectAreaService = beanFactory.getBean(ISubjectAreaService.class);
        subjectAreaService.read();
        @NotNull final File folder = new File("data");
        if (!folder.exists()) folder.mkdir();
        @NotNull final File file = new File("data/fasterxml.xml");
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        xmlMapper.setDateFormat(df);
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, subjectAreaService);
        terminalService.showMessage("DATA SAVED");
    }
}
