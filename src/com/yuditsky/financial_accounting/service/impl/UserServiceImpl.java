package com.yuditsky.financial_accounting.service.impl;

import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.DAOFactory;
import com.yuditsky.financial_accounting.dao.UserDAO;
import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.UserService;

public class UserServiceImpl implements UserService {
    private static final char paramDelimiter = ' ';

    @Override
    public boolean signIn(String login, String password) throws ServiceException {

        if (login == null || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }

        if (password == null || password.isEmpty()) {
            throw new ServiceException("Incorrect password");
        }

        String authorizationData;

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            authorizationData = userDAO.signIn();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return login.equals(authorizationData.substring(0, authorizationData.indexOf(paramDelimiter))) &&
                password.equals(authorizationData.substring(authorizationData.indexOf(paramDelimiter) + 1));

    }
}
