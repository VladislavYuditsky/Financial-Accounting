package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.UserService;

public class SignIn implements Command {

    private final char paramDelimiter = ' ';

    @Override
    public String execute(String request) throws StringIndexOutOfBoundsException {

        String response = null;

        request = request.substring(request.indexOf(paramDelimiter) + 1);

        String login = request.substring(0, request.indexOf(paramDelimiter));

        request = request.replaceFirst(login, "");
        request = request.replaceFirst(" ", "");

        String password = request;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            if (userService.signIn(login, password)) {
                response = "Welcome";
            } else {
                response = "Wrong login or password";
            }

        } catch (ServiceException e) {
            response = "Error during login procedure";
        }

        return response;
    }
}
