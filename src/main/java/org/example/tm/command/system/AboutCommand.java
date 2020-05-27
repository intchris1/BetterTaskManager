package org.example.tm.command.system;

import com.jcabi.manifests.Manifests;
import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static org.example.tm.command.CommandInfo.ABOUT_COMMAND;

@Component
public class AboutCommand extends AbstractCommand {
    public AboutCommand() {
        super(false);
    }

    @NotNull
    @Override
    public String getName() {
        return ABOUT_COMMAND.getName();
    }

    @NotNull
    @Override
    public String getDescription() {
        return ABOUT_COMMAND.getDescription();
    }

    @Override
    public void execute() {
        terminalService.showMessage("APPLICATION DESCRIPTION");
        String buildNumber = Manifests.read("buildNumber");
        String developerName = Manifests.read("developer");
        terminalService.showMessage("Build number is " + buildNumber);
        terminalService.showMessage("Developed by " + developerName);
    }
}
