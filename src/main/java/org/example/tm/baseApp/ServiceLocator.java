package org.example.tm.baseApp;

import org.example.tm.baseApp.service.*;
import org.example.tm.session.SessionService;
import org.jetbrains.annotations.NotNull;

public interface ServiceLocator {
    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    ITerminalService getTerminalService();

    @NotNull
    SessionService getSessionService();

    @NotNull
    ISubjectAreaService getSubjectAreaService();

}
