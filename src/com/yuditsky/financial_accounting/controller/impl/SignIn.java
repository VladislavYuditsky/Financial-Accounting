package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.UserService;

public class SignIn implements Command {

    @Override
    public String execute(String request) {
        String login = null;
        String password = null;
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            userService.signIn(login, password);
            response = "Welcome!";
        } catch (ServiceException e) {
            response = "Error during login procedure";
        }

        return response;
    }
}
