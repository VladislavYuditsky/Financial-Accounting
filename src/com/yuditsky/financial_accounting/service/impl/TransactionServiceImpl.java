package com.yuditsky.financial_accounting.service.impl;

import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.DAOFactory;
import com.yuditsky.financial_accounting.dao.TransactionDAO;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public String readTransactions() throws ServiceException {

        String transactions;

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
    public void add() throws ServiceException {

    }

    @Override
    public void edit() throws ServiceException {

    }

    @Override
    public void delete() throws ServiceException {

    }
}
