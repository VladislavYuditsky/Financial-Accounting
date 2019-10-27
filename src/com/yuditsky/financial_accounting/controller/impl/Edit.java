package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

import static com.yuditsky.financial_accounting.service.util.Constants.*;

public class Edit implements Command {

    @Override
    public String execute(String request) {

        String response;

        try {
            request = request.substring(request.indexOf(PARAM_DELIMITER) + 1);

            String strId = request.substring(0, request.indexOf(PARAM_DELIMITER));
            int id = Integer.parseInt(strId);

            request = request.replaceFirst(strId, "");
            request = request.replaceFirst(" ", "");

            String strAmount = request;
            double amount = Double.parseDouble(strAmount);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            TransactionService transactionService = serviceFactory.getTransactionService();

            if (transactionService.editAmount(id, amount)) {
                response = EDITED;
            } else {
                response = NO_TRANSACTION_WITH_GIVEN_ID;
            }
        } catch (ServiceException e) {
            response = EDITING_ERROR;
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            response = INVALID_COMMAND_ARGUMENTS;
        }

        return response;
    }
}
