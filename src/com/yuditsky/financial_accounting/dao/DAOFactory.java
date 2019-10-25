package com.yuditsky.financial_accounting.dao;

import com.yuditsky.financial_accounting.dao.impl.FileTransactionDAO;
import com.yuditsky.financial_accounting.dao.impl.FileUserDAO;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final TransactionDAO transactionDAOImpl = new FileTransactionDAO();
    private final UserDAO userDAOImpl = new FileUserDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public TransactionDAO getTransasctionDAO() {
        return transactionDAOImpl;
    }

    public UserDAO getUserDAO() {
        return userDAOImpl;
    }

}
