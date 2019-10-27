package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

public class Delete implements Command {

    private final char paramDelimiter = ' ';

    @Override
    public String execute(String request) {
        String response;

        try {
            request = request.substring(request.indexOf(paramDelimiter) + 1);

            int id = Integer.parseInt(request);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            TransactionService transactionService = serviceFactory.getTransactionService();

            if (transactionService.delete(id)) {
                response = "Deleted";
            } else {
                response = "No transaction with given id";
            }

        } catch (ServiceException e) {
            response = "Error during deletion";
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            response = "Invalid command arguments";
        }

        return response;
    }
}
