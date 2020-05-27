package org.example.tm.config;

import org.example.tm.command.AbstractCommand;
import org.example.tm.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CommandFactoryConfig {

    @Autowired
    private List<AbstractCommand> commands;

    @Bean
    public CommandFactory commandFactory() {
        Map<String, AbstractCommand> commandByName = commands.stream()
                .collect(Collectors.toUnmodifiableMap(AbstractCommand::getName, it -> it));
        return new CommandFactory(commandByName);
    }

}
