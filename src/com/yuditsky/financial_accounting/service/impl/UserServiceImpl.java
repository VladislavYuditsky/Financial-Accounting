package com.yuditsky.financial_accounting.service.impl;

import com.yuditsky.financial_accounting.service.ServiceException;
import com.yuditsky.financial_accounting.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void signIn(String login, String password) throws ServiceException {
        /*
        if (login == null || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }
        // реализуем функционал логинации пользователя в системе
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        //....
         */
    }
}
