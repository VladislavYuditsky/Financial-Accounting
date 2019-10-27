package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

import static com.yuditsky.financial_accounting.service.util.Constants.*;

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
                response = DELETED;
            } else {
                response = NO_TRANSACTION_WITH_GIVEN_ID;
            }

        } catch (ServiceException e) {
            response = DELETION_ERROR;
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            response = INVALID_COMMAND_ARGUMENTS;
        }

        return response;
    }
}
