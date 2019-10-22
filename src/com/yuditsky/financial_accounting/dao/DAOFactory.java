package com.yuditsky.financial_accounting.dao;

import com.yuditsky.financial_accounting.dao.impl.FileAccountDAO;
import com.yuditsky.financial_accounting.dao.impl.FileUserDAO;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final AccountDAO accountDAOImpl = new FileAccountDAO();
    private final UserDAO userDAOImpl = new FileUserDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public AccountDAO getAccountDAO() {
        return accountDAOImpl;
    }

    public UserDAO getUserDAO() {
        return userDAOImpl;
    }

}
