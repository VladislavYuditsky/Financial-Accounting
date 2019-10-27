package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.UserService;

import static com.yuditsky.financial_accounting.service.util.Constants.*;

public class SignIn implements Command {

    @Override
    public String execute(String request) throws StringIndexOutOfBoundsException {

        String response = null;

        request = request.substring(request.indexOf(PARAM_DELIMITER) + 1);

        String login = request.substring(0, request.indexOf(PARAM_DELIMITER));

        request = request.replaceFirst(login, "");
        request = request.replaceFirst(" ", "");

        String password = request;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            if (userService.signIn(login, password)) {
                response = WELCOME;
            } else {
                response = WRONG_LOGIN_OR_PASSWORD;
            }

        } catch (ServiceException e) {
            response = LOGIN_ERROR;
        }

        return response;
    }
}
