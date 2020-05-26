package org.example.tm.baseApp.service;

import org.example.tm.baseApp.ServiceLocator;
import org.jetbrains.annotations.NotNull;

public interface ISubjectAreaService {

    void write(@NotNull ServiceLocator serviceLocator);

    void read(@NotNull ServiceLocator serviceLocator);

}
