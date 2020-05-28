package org.example.tm.baseApp.service;

import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public interface ITerminalService {

    void showMessage(@NotNull final String message);

    @NotNull
    String readLine() throws IOException;

    void printList(List<? extends AbstractEntity> list);

}
