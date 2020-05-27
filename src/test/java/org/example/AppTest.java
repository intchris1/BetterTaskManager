package org.example;

import org.example.tm.baseApp.repository.IProjectRepository;
import org.example.tm.baseApp.repository.ITaskRepository;
import org.example.tm.baseApp.repository.IUserRepository;
import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ISubjectAreaService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.command.AbstractCommand;
import org.example.tm.command.CommandFactory;
import org.example.tm.command.data.load.DataFasterxmlJsonLoadCommand;
import org.example.tm.command.data.save.DataFasterxmlJsonSaveCommand;
import org.example.tm.command.project.ProjectClearCommand;
import org.example.tm.command.system.HelpCommand;
import org.example.tm.command.task.TaskCreateCommand;
import org.example.tm.command.user.UserCreateCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest extends AbstractSpringTest {

    @Autowired
    private ApplicationContext ctx;

    @Test
    void assertCtxInitialized() {
        assertNotNull(ctx);
    }

    @Test
    void assertUserRepositoryInitialized() {
        IUserRepository bean1 = ctx.getBean(IUserRepository.class);
        IUserRepository bean2 = ctx.getBean(IUserRepository.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertTaskRepositoryInitialized() {
        ITaskRepository bean1 = ctx.getBean(ITaskRepository.class);
        ITaskRepository bean2 = ctx.getBean(ITaskRepository.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertProjectRepositoryInitialized() {
        IProjectRepository bean1 = ctx.getBean(IProjectRepository.class);
        IProjectRepository bean2 = ctx.getBean(IProjectRepository.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }
//
    @Test
    void assertProjectServiceInitialized() {
        IProjectService bean1 = ctx.getBean(IProjectService.class);
        IProjectService bean2 = ctx.getBean(IProjectService.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertTaskServiceInitialized() {
        ITaskService bean1 = ctx.getBean(ITaskService.class);
        ITaskService bean2 = ctx.getBean(ITaskService.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertProjectClearCommandInitialized() {
        ProjectClearCommand bean1 = ctx.getBean(ProjectClearCommand.class);
        ProjectClearCommand bean2 = ctx.getBean(ProjectClearCommand.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertUserCreateCommandInitialized() {
        UserCreateCommand bean1 = ctx.getBean(UserCreateCommand.class);
        UserCreateCommand bean2 = ctx.getBean(UserCreateCommand.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertTaskCreateCommandInitialized() {
        TaskCreateCommand bean1 = ctx.getBean(TaskCreateCommand.class);
        TaskCreateCommand bean2 = ctx.getBean(TaskCreateCommand.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertCommandFactoryInitialized() {
        CommandFactory bean = ctx.getBean(CommandFactory.class);
        assertNotNull(bean);
        AbstractCommand help = bean.getCommand("help");
        assertEquals(HelpCommand.class, help.getClass());
    }

    @Test
    void assertSubjectServiceInitialized() {
        ISubjectAreaService bean1 = ctx.getBean(ISubjectAreaService.class);
        ISubjectAreaService bean2 = ctx.getBean(ISubjectAreaService.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertNotEquals(bean1, bean2);
    }

    @Test
    void assertDataFasterxmlJsonLoadCommandInitialized() {
        DataFasterxmlJsonLoadCommand bean1 = ctx.getBean(DataFasterxmlJsonLoadCommand.class);
        DataFasterxmlJsonLoadCommand bean2 = ctx.getBean(DataFasterxmlJsonLoadCommand.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

    @Test
    void assertDataFasterxmlJsonSaveCommandInitialized() {
        DataFasterxmlJsonSaveCommand bean1 = ctx.getBean(DataFasterxmlJsonSaveCommand.class);
        DataFasterxmlJsonSaveCommand bean2 = ctx.getBean(DataFasterxmlJsonSaveCommand.class);
        assertNotNull(bean1);
        assertNotNull(bean2);
        assertEquals(bean1, bean2);
    }

}
