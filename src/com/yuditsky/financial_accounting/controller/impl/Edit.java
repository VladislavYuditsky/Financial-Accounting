package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

public class Edit implements Command {

    private final char paramDelimiter = ' ';

    @Override
    public String execute(String request) {

        String response;

        try {
            request = request.substring(request.indexOf(paramDelimiter) + 1);

            String strId = request.substring(0, request.indexOf(paramDelimiter));
            int id = Integer.parseInt(strId);

            request = request.replaceFirst(strId, "");
            request = request.replaceFirst(" ", "");

            String strAmount = request;
            double amount = Double.parseDouble(strAmount);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            TransactionService transactionService = serviceFactory.getTransactionService();

            if (transactionService.editAmount(id, amount)) {
                response = "Edited";
            } else {
                response = "No transaction with given id";
            }
        } catch (ServiceException e) {
            response = "Error during editing";
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            response = "Invalid command arguments";
        }

        return response;
    }
}
