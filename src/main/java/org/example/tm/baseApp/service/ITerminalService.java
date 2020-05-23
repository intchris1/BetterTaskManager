package org.example.tm.baseApp.service;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface ITerminalService {
    void showMessage(@NotNull final String message);

    @NotNull
    String readLine() throws IOException;
}
