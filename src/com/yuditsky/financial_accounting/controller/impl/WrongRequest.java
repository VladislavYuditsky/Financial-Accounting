package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;

import static com.yuditsky.financial_accounting.service.util.Constants.WRONG_REQUEST;

public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return WRONG_REQUEST;
    }
}
