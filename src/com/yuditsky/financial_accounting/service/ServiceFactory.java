package com.yuditsky.financial_accounting.service;

import com.yuditsky.financial_accounting.service.impl.TransactionServiceImpl;
import com.yuditsky.financial_accounting.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    private UserService userServiceImpl = new UserServiceImpl();
    private TransactionService transactionServiceImpl = new TransactionServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public TransactionService getTransactionService() {
        return transactionServiceImpl;
    }

    public UserService getUserService() {
        return userServiceImpl;
    }
}
