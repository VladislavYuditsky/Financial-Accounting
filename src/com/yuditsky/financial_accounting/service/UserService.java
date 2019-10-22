package com.yuditsky.financial_accounting.service;

public interface UserService {
    void signIn(String login, String password) throws ServiceException;
}
