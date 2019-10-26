package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.bean.*;
import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

public class Add implements Command {

    private final char paramDelimiter = ' ';

    @Override
    public String execute(String request) {
        String response;

        try {

            request = request.substring(request.indexOf(paramDelimiter) + 1);

            String transactionType = request.substring(0, request.indexOf(paramDelimiter));

            request = request.replaceFirst(transactionType, "");
            request = request.replaceFirst(" ", "");

            String strAmount = request.substring(0, request.indexOf(paramDelimiter));
            Double amount = Double.parseDouble(strAmount);

            request = request.replaceFirst(strAmount, "");
            request = request.replaceFirst(" ", "");

            Transaction transaction = null;

            if(transactionType.equals(Payment.class.getSimpleName().toLowerCase())){
                PaymentType type = PaymentType.valueOf(request);
                transaction = new Payment(amount, type);
            } else {
                PayrollType type = PayrollType.valueOf(request);
                transaction = new Payroll(amount, type);
            }

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
