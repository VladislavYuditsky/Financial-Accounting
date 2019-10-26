package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

public class ReadTransactions implements Command {
    @Override
    public String execute(String request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TransactionService transactionService = serviceFactory.getTransactionService();

        String response;

        try {
            response = transactionService.readTransactions();

            if(response.equals("")){
                response = "No operations";
            }

        } catch (ServiceException e) {
            response = "Error during read procedure";
        }


        return response;
    }
}
