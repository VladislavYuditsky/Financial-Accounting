package com.yuditsky.financial_accounting.service;

public interface UserService {
    boolean signIn(String login, String password) throws ServiceException;

}
