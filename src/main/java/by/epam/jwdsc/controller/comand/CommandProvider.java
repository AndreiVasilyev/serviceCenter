package by.epam.jwdsc.controller.comand;

import by.epam.jwdsc.controller.comand.impl.DefaultCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;


public class CommandProvider {

    private static final Logger log = LogManager.getLogger();
    private static CommandProvider instance;
    private final EnumMap<CommandName, Command> commands;

    private CommandProvider() {
        commands = new EnumMap<CommandName, Command>(CommandName.class);
        commands.put(CommandName.DEFAULT, new DefaultCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String name) {
        Command command;
        if (name == null || name.isBlank()) {
            log.error("Command name is null or blank");
            command = commands.get(CommandName.DEFAULT);
        } else {
            try {
                CommandName commandName = CommandName.valueOf(name.toUpperCase());
                command = commands.get(commandName);
            } catch (IllegalArgumentException e) {
                log.error("Command {} not found", name, e);
                command = commands.get(CommandName.DEFAULT);
            }
        }
        return command;
    }
}
