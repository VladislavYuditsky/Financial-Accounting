package com.yuditsky.financial_accounting.controller.impl;

import com.yuditsky.financial_accounting.bean.Payment;
import com.yuditsky.financial_accounting.bean.Payroll;
import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.controller.command.Command;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

import java.util.List;

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
            for (int i = 0; i < transactions.size(); i++) {
                if (Payment.class.equals(transactions.get(i).getClass())) {
                    stringBuffer.append(transactions.get(i).getId() + " " + Payment.class.getSimpleName().toLowerCase() + " "
                            + transactions.get(i).getAmount() + " " + ((Payment) transactions.get(i)).getType() + "\n");
                } else {
                    stringBuffer.append(transactions.get(i).getId() + " " + Payroll.class.getSimpleName().toLowerCase() + " "
                            + transactions.get(i).getAmount() + " " + ((Payroll) transactions.get(i)).getType() + "\n");
                }
            }

            response = String.valueOf(stringBuffer);

            if (response.equals("")) {
                response = "No transaction";
            }
        } catch (ServiceException e) {
            response = "Error during read procedure";
        }

        return response;
    }
}
