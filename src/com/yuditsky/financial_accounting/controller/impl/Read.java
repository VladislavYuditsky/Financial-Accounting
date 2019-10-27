package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.bean.Payment;
import com.yuditsky.financial_accounting.bean.Payroll;
import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;
import com.yuditsky.financial_accounting.service.util.Parser;

import java.util.List;

import static com.yuditsky.financial_accounting.service.util.Constants.*;

public class Read implements Command {
    @Override
    public String execute(String request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TransactionService transactionService = serviceFactory.getTransactionService();

        StringBuffer stringBuffer = new StringBuffer();
        String response;
        List<Transaction> transactions;

        try {

            transactions = transactionService.readTransactions();
            Parser parser = Parser.getInstance();

            for(Transaction transaction : transactions){
                stringBuffer.append(parser.parseString(transaction)).append(NEW_LINE);
            }

            response = String.valueOf(stringBuffer);

            if (response.equals(EMPTY_STRING)) {
                response = NO_TRANSACTION;
            }
        } catch (ServiceException e) {
            response = READING_ERROR;
        }

        return response;
    }
}
