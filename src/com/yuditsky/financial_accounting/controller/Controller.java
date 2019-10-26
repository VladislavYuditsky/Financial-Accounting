package com.yuditsky.financial_accounting.controller;

import com.yuditsky.financial_accounting.bean.User;
import com.yuditsky.financial_accounting.controller.command.Command;

public class Controller {

    private final CommandProvider provider = new CommandProvider();

    private final char paramDelimiter = ' ';

    private User user = new User();

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;
        String response = null;

        try {
            commandName = request.substring(0, request.indexOf(paramDelimiter));

            if (commandName.equals(CommandName.SIGN_IN.toString().toLowerCase()) && user.isAuthorized()) {
                return "You are already logged in";
            }

            if (!commandName.equals(CommandName.SIGN_IN.toString().toLowerCase()) && !user.isAuthorized()) {
                return "Log in";
            }

            executionCommand = provider.getCommand(commandName);


            response = executionCommand.execute(request);

            if (commandName.equals(CommandName.SIGN_IN.toString().toLowerCase()) && response.equals("Welcome")) {
                user.setAuthorized(true);
            }
        }catch(StringIndexOutOfBoundsException e){
            response = executeTask(request + " ");
        }

        return response;
    }
}
