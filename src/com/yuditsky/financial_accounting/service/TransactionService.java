package com.yuditsky.financial_accounting.service;

import com.yuditsky.financial_accounting.bean.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> readTransactions() throws ServiceException;

    void add(Transaction transaction) throws ServiceException;

    boolean editAmount(int id, double amount) throws ServiceException;

    boolean delete(int id) throws ServiceException;
}
