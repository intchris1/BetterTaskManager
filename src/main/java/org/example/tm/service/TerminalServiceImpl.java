package org.example.tm.service;

import lombok.NoArgsConstructor;
import org.example.tm.baseApp.service.ITerminalService;
import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Component
public final class TerminalServiceImpl implements ITerminalService {

    private final BufferedReader reader;

    public TerminalServiceImpl(BufferedReader reader) {
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

    @Override
    public void printList(List<? extends AbstractEntity> list) {
        if (list.isEmpty()) System.out.println("THE LIST IS EMPTY");
        else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s\n", i+1, list.get(i).getName());
            }
        }
    }

}
