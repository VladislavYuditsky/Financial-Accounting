package com.yuditsky.financial_accounting.service;

import com.yuditsky.financial_accounting.bean.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> readTransactions() throws ServiceException;

    void add(Transaction transaction) throws ServiceException;

    void edit() throws ServiceException;

    void delete() throws ServiceException;
}
