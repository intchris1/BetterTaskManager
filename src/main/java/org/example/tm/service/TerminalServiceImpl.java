package org.example.tm.service;

import lombok.NoArgsConstructor;
import org.example.tm.baseApp.service.ITerminalService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;

@NoArgsConstructor
public final class TerminalServiceImpl implements ITerminalService {

    private BufferedReader reader;

    public TerminalServiceImpl(
            @NotNull final BufferedReader reader
    ) {
        this.reader = reader;
    }

    @Override
    public void showMessage(@Nullable final String message) {
        System.out.println(message);
    }

    @NotNull
    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }

}
