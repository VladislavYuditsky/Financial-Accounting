package com.yuditsky.financial_accounting.dao;

import java.util.List;

public interface TransactionDAO {

    String readTransactions() throws DAOException;

    void add(String transaction) throws DAOException;

    void edit() throws DAOException;

    void delete() throws DAOException;
}
