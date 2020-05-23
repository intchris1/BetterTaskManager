package org.example.tm.command.system;

import com.jcabi.manifests.Manifests;
import org.example.tm.command.AbstractCommand;
import org.jetbrains.annotations.NotNull;

import static org.example.tm.command.CommandInfo.ABOUT_COMMAND;

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
        serviceLocator.getTerminalService().showMessage("APPLICATION DESCRIPTION");
        String buildNumber = Manifests.read("buildNumber");
        String developerName = Manifests.read("developer");
        serviceLocator.getTerminalService().showMessage("Build number is " + buildNumber);
        serviceLocator.getTerminalService().showMessage("Developed by " + developerName);
    }
}
