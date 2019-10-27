package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.bean.*;
import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;
import com.yuditsky.financial_accounting.service.util.TransactionIdGenerator;

import static com.yuditsky.financial_accounting.service.util.Constants.*;

public class Add implements Command {

    @Override
    public String execute(String request) {
        String response;

        try {

            request = request.substring(request.indexOf(PARAM_DELIMITER) + 1);

            String transactionType = request.substring(0, request.indexOf(PARAM_DELIMITER));

            request = request.replaceFirst(transactionType, EMPTY_STRING);
            request = request.replaceFirst(PARAM_DELIMITER, EMPTY_STRING);

            String strAmount = request.substring(0, request.indexOf(PARAM_DELIMITER));
            Double amount = Double.parseDouble(strAmount);

            request = request.replaceFirst(strAmount, EMPTY_STRING);
            request = request.replaceFirst(PARAM_DELIMITER, EMPTY_STRING);

            Transaction transaction;

            TransactionIdGenerator transactionIdGenerator = TransactionIdGenerator.getInstance();
            int id = transactionIdGenerator.generate();

            if (transactionType.equals(Payment.class.getSimpleName().toLowerCase())) {
                PaymentType type = PaymentType.valueOf(request);
                transaction = new Payment(id, amount, type);
            } else {
                PayrollType type = PayrollType.valueOf(request);
                transaction = new Payroll(id, amount, type);
            }

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            TransactionService transactionService = serviceFactory.getTransactionService();

            transactionService.add(transaction);
            response = ADDED;
        } catch (ServiceException e) {
            response = ADDING_ERROR;
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            response = INVALID_COMMAND_ARGUMENTS;
        }

        return response;
    }
}
