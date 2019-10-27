package com.yuditsky.financial_accounting.service.util;

import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.ServiceFactory;
import com.yuditsky.financial_accounting.service.TransactionService;

import java.util.Comparator;
import java.util.List;

public class TransactionIdGenerator {

    private static final TransactionIdGenerator instance = new TransactionIdGenerator();

    private TransactionIdGenerator() {
    }

    public static TransactionIdGenerator getInstance() {
        return instance;
    }

    public int generate() throws ServiceException {

        try {

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            TransactionService transactionService = serviceFactory.getTransactionService();

            List<Transaction> transactions = transactionService.readTransactions();

            transactions.sort(Comparator.comparing(Transaction::getId));

            if (transactions.size() != 0) {
                int i;
                for (i = 0; i < transactions.size(); i++) {
                    if (transactions.get(i).getId() != i + 1) {
                        return i + 1;
                    }
                }
                return i + 1;
            } else {
                return 1;
            }

        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }
}
