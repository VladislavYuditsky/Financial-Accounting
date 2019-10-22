package com.yuditsky.financial_accounting.service;

import com.yuditsky.financial_accounting.service.impl.AccountServiceImpl;
import com.yuditsky.financial_accounting.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    private UserService userServiceImpl = new UserServiceImpl();
    private AccountService accountServiceImpl = new AccountServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public AccountService getAccountService() {
        return accountServiceImpl;
    }

    public UserService getUserService() {
        return userServiceImpl;
    }
}
