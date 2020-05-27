package org.example.tm.command;

import java.util.Map;

public class CommandFactory {

    private final Map<String, AbstractCommand> commands;

    public CommandFactory(Map<String, AbstractCommand> commands) {
        System.out.println("В конструктор добавлены команды:");
        System.out.println(commands);
        this.commands = commands;
    }

    public AbstractCommand getCommand(String name) {
        return commands.get(name);
    }
}
