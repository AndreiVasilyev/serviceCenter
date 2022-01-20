package by.epam.jwdsc.controller.command;

import by.epam.jwdsc.controller.command.impl.ChangeLocaleCommand;
import by.epam.jwdsc.controller.command.impl.DefaultCommand;
import by.epam.jwdsc.controller.command.impl.FindOrderByNumberCommand;
import by.epam.jwdsc.controller.command.impl.SendCodeCommand;
import by.epam.jwdsc.controller.command.impl.gotocommand.GotoCheckOrderPageCommand;
import by.epam.jwdsc.controller.command.impl.gotocommand.GotoErrorPageCommand;
import by.epam.jwdsc.controller.command.impl.gotocommand.GotoMainPageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

import static by.epam.jwdsc.controller.command.CommandName.*;


public class CommandProvider {

    private static final Logger log = LogManager.getLogger();
    private static CommandProvider instance;
    private final EnumMap<CommandName, Command> commands;

    private CommandProvider() {
        commands = new EnumMap<CommandName, Command>(CommandName.class);
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(GOTO_MAIN_PAGE, new GotoMainPageCommand());
        commands.put(GOTO_CHECK_ORDER_PAGE, new GotoCheckOrderPageCommand());
        commands.put(SEND_CODE_COMMAND, new SendCodeCommand());
        commands.put(FIND_ORDER_BY_NUMBER, new FindOrderByNumberCommand());
        commands.put(GOTO_ERROR_PAGE, new GotoErrorPageCommand());
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
