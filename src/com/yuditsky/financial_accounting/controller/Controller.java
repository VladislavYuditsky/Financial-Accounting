package com.yuditsky.financial_accounting.controller;

import com.yuditsky.financial_accounting.bean.User;
import com.yuditsky.financial_accounting.controller.command.Command;

import static com.yuditsky.financial_accounting.service.util.Constants.*;

public class Controller {

    private final CommandProvider provider = new CommandProvider();

    private User user = new User();

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;
        String response;

        try {
            commandName = request.substring(0, request.indexOf(PARAM_DELIMITER));

            if (commandName.equals(CommandName.SIGN_IN.toString().toLowerCase()) && user.isAuthorized()) {
                return ALREADY_LOGGED;
            }

            if (!commandName.equals(CommandName.SIGN_IN.toString().toLowerCase()) && !user.isAuthorized()) {
                return LOG_IN;
            }

            executionCommand = provider.getCommand(commandName);

            response = executionCommand.execute(request);

            if (commandName.equals(CommandName.SIGN_IN.toString().toLowerCase()) && response.equals(WELCOME)) {
                user.setAuthorized(true);
            }
        } catch (StringIndexOutOfBoundsException e) {
            response = executeTask(request + PARAM_DELIMITER);
        }

        return response;
    }
}
