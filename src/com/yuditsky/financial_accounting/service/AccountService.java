package com.yuditsky.financial_accounting.service;

public interface AccountService {
    void payroll(String amount) throws ServiceException;

    void payment(String amount) throws ServiceException;
}
