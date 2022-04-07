package by.epam.jwdsc.controller.command;

import by.epam.jwdsc.controller.command.impl.*;
import by.epam.jwdsc.controller.command.impl.findcommand.*;
import by.epam.jwdsc.controller.command.impl.gotocommand.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

import static by.epam.jwdsc.controller.command.CommandName.*;
import static by.epam.jwdsc.controller.command.RequestParameter.COMMAND_PARAM;


public class CommandProvider {

    private static final Logger log = LogManager.getLogger();
    private static CommandProvider instance;
    private final EnumMap<CommandName, Command> commands;

    private CommandProvider() {
        commands = new EnumMap<CommandName, Command>(CommandName.class);
        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(GOTO_MAIN_PAGE, new GotoMainPageCommand());
        commands.put(GOTO_CHECK_ORDER_PAGE, new GotoCheckOrderPageCommand());
        commands.put(SEND_CODE, new SendCodeCommand());
        commands.put(FIND_ORDER_BY_NUMBER, new FindOrderByNumberCommand());
        commands.put(GOTO_ERROR_PAGE, new GotoErrorPageCommand());
        commands.put(GOTO_LOGIN_PAGE, new GotoLoginPageCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(GOTO_REGISTRATION_PAGE, new GotoRegistrationPage());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(GOTO_CONTROL_PAGE, new GotoControlPageCommand());
        commands.put(FIND_ORDERS, new FindOrdersCommand());
        commands.put(FIND_ALL_COMPANIES_DEVICES, new FindAllCompaniesDevicesCommand());
        commands.put(FIND_CLIENTS_BY_PHONE, new FindClientsByPhoneCommand());
        commands.put(FIND_CLIENT_BY_ID, new FindClientByIdCommand());
        commands.put(SAVE_NEW_ORDER, new SaveNewOrderCommand());
        commands.put(FIND_ORDER_BY_ID, new FindOrderByIdCommand());
        commands.put(FIND_ALL_SELECTABLE_ITEMS, new FindAllSelectableItemsCommand());
        commands.put(FIND_PARTS_BY_PARAM, new FindPartsByParamCommand());
        commands.put(FIND_WORK_COST, new FindWorkCostCommand());
        commands.put(UPDATE_ORDER, new UpdateOrderCommand());
        commands.put(REMOVE_ORDER_BY_ID, new RemoveOrderByIdCommand());
        commands.put(TAKE_ORDER_TO_WORK, new TakeOrderToWorkCommand());
        commands.put(ISSUE_ORDER, new IssueOrderCommand());
        commands.put(FIND_EMPLOYEES, new FindEmployeesCommand());
        commands.put(CHANGE_EMPLOYEE_ROLE, new ChangeEmployeeRoleCommand());
        commands.put(CHECK_LOGIN, new CheckLoginCommand());
        commands.put(REGISTER_EMPLOYEE, new RegisterEmployeeCommand());
        commands.put(REGISTRATION_FIRST_STEP, new RegistrationFirstStepCommand());
        commands.put(REGISTRATION_FINAL, new RegistrationFinalCommand());
        commands.put(ADD_NEW_PART, new AddNewPartCommand());
        commands.put(FIND_PARTS, new FindPartsCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        String name = request.getParameter(COMMAND_PARAM);
        Command command;
        try {
            if (name == null || name.isBlank()) {
                log.error("Command name is null or blank");
                throw new IllegalArgumentException("Command name is null or blank");
            } else {
                CommandName commandName = CommandName.valueOf(name.toUpperCase());
                command = commands.get(commandName);
            }
        } catch (IllegalArgumentException e) {
            log.error("Command {} not found", name, e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            command = commands.get(GOTO_ERROR_PAGE);
        }
        return command;
    }
}
