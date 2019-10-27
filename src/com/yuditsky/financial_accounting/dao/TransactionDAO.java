package com.yuditsky.financial_accounting.dao;

import com.yuditsky.financial_accounting.bean.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> readTransactions() throws DAOException;

    void add(Transaction transaction) throws DAOException;

    Transaction read(int id) throws DAOException;

    boolean replace(int id, Transaction transaction) throws DAOException;

    boolean delete(int id) throws DAOException;
}
