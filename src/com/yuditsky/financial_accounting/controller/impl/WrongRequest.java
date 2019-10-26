package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;

public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return "Wrong request";
    }
}
