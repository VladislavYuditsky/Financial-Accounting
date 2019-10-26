package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.bean.Payment;
import com.yuditsky.financial_accounting.bean.PaymentType;
import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

public class AddPayment implements Command {

    private final char paramDelimiter = ' ';

    @Override
    public String execute(String request) {
        String response;

        try {

            request = request.substring(request.indexOf(paramDelimiter) + 1);

            String strAmount = request.substring(0, request.indexOf(paramDelimiter));
            Double amount = Double.parseDouble(strAmount);

            request = request.replaceFirst(strAmount, "");
            request = request.replaceFirst(" ", "");

            PaymentType type = PaymentType.valueOf(request);

            Transaction transaction = new Payment(amount, type);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            TransactionService transactionService = serviceFactory.getTransactionService();

            transactionService.add(transaction);
            response = "Added";
        } catch (ServiceException e) {
            response = "Error during adding";
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            response = "Invalid command arguments";
        }

        return response;
    }
}
