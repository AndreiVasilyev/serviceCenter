package by.epam.jwdsc.controller.comand;

import by.epam.jwdsc.controller.comand.impl.DefaultCommand;

import java.util.EnumMap;


public class CommandProvider {

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
            command = commands.get(CommandName.DEFAULT);
        }
        try {
            CommandName commandName = CommandName.valueOf(name.toUpperCase());
            command = commands.get(commandName);
        } catch (IllegalArgumentException e) {
            command = commands.get(CommandName.DEFAULT);
        }
        return command;
    }
}
