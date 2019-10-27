package com.yuditsky.financial_accounting.controller;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.ADD, new Add());
        repository.put(CommandName.READ, new Read());
        repository.put(CommandName.EDIT, new Edit());
        repository.put(CommandName.DELETE, new Delete());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
