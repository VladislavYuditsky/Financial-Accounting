package com.yuditsky.financial_accounting.dao;

import com.yuditsky.financial_accounting.bean.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> readTransactions() throws DAOException;

    void add(Transaction transaction) throws DAOException;

    void edit() throws DAOException;

    void delete() throws DAOException;
}
