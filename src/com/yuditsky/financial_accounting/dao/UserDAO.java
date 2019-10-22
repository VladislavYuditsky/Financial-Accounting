package com.yuditsky.financial_accounting.dao;

public interface UserDAO {
    void signIn(String login, String password) throws DAOException;
}