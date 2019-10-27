package com.yuditsky.financial_accounting.service.impl;

import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.DAOFactory;
import com.yuditsky.financial_accounting.dao.TransactionDAO;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.TransactionService;

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
    public boolean editAmount(int id, double amount) throws ServiceException {

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        TransactionDAO transactionDAO = daoObjectFactory.getTransactionDAO();

        Transaction transaction;

        try {
            transaction = transactionDAO.read(id);

            if (transaction != null) {
                transaction.setAmount(amount);
            } else {
                return false;
            }

            transactionDAO.replace(id, transaction);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return true;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        boolean deleted = false;

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            TransactionDAO transactionDAO = daoObjectFactory.getTransactionDAO();

            if (transactionDAO.delete(id)) {
                deleted = true;
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return deleted;
    }
}
