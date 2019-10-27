package com.yuditsky.financial_accounting.service.impl;

import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.DAOFactory;
import com.yuditsky.financial_accounting.dao.TransactionDAO;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public List<Transaction> readTransactions() throws ServiceException {

        List<Transaction> transactions;

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            TransactionDAO transactionDAO = daoObjectFactory.getTransactionDAO();

            transactions = transactionDAO.readTransactions();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return transactions;
    }

    @Override
    public void add(Transaction transaction) throws ServiceException {
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            TransactionDAO transactionDAO = daoObjectFactory.getTransactionDAO();

            transactionDAO.add(transaction);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void edit() throws ServiceException {

    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            TransactionDAO transactionDAO = daoObjectFactory.getTransactionDAO();

            transactionDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
