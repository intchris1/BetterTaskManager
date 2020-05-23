package org.example.tm.command;


import org.example.tm.baseApp.ServiceLocator;
import org.example.tm.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public abstract class AbstractCommand {
    protected ServiceLocator serviceLocator;
    private final boolean isSecure;

    public AbstractCommand(final boolean isSecure) {
        this.isSecure = isSecure;
    }

    public void setServiceLocator(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public boolean isSecure() {
        return isSecure;
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDescription();

    public abstract void execute() throws IOException;

    public void printList(List<? extends AbstractEntity> list) {
        if (list.isEmpty()) System.out.println("THE LIST IS EMPTY");
        else {
            int numberInList = 0;
            for (AbstractEntity entity : list) {
                numberInList++;
                System.out.printf("%d. %s\n", numberInList, entity.getName());
            }
        }
    }

}
