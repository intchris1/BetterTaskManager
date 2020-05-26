package org.example;

import org.example.tm.command.AbstractCommand;
import org.example.tm.context.Bootstrap;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {

        @NotNull final Bootstrap bootstrap = new Bootstrap();
        @NotNull final Set<Class<? extends AbstractCommand>> classes =
                new Reflections("org.example.tm").getSubTypesOf(AbstractCommand.class);

        bootstrap.init(classes);
    }
}
