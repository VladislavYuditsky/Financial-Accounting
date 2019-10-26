package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.bean.Payment;
import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

public class Read implements Command {
    @Override
    public String execute(String request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TransactionService transactionService = serviceFactory.getTransactionService();

        StringBuffer stringBuffer = new StringBuffer();
        String response;

        try {

            for (int i = 0; i < transactionService.readTransactions().size(); i++) {
                Transaction transaction = transactionService.readTransactions().get(i);
                if ("payment".equals(transaction.getClass().getSimpleName().toLowerCase())) {
                    stringBuffer.append(transaction.getId() + " " + "payment" + " "
                            + transaction.getAmount() + " " + ((Payment) transaction).getType());
                }
            }

            response = String.valueOf(stringBuffer);
        } catch (ServiceException e) {
            response = "Error during read procedure";
        }

        return response;
    }
}
