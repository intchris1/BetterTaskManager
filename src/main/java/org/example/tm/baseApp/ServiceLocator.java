package org.example.tm.baseApp;

import org.example.tm.baseApp.service.IProjectService;
import org.example.tm.baseApp.service.ITaskService;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.baseApp.service.IUserService;
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
}
