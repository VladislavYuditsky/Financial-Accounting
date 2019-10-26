package com.yuditsky.financial_accounting.service;

public interface TransactionService {

    String readTransactions() throws ServiceException;

    void add() throws ServiceException;

    void edit() throws ServiceException;

    void delete() throws ServiceException;
}
