package com.yuditsky.financial_accounting.dao.impl;

import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.UserDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.yuditsky.financial_accounting.service.util.Constants.AUTHORIZATION_DATA_FILE_PATH;
import static com.yuditsky.financial_accounting.service.util.Constants.PARAM_DELIMITER;

public class FileUserDAO implements UserDAO {

    @Override
    public String signIn() throws DAOException {
        String authorizationData = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(AUTHORIZATION_DATA_FILE_PATH))) {

            authorizationData += bufferedReader.readLine();
            authorizationData += PARAM_DELIMITER;
            authorizationData += bufferedReader.readLine();

        } catch (FileNotFoundException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return authorizationData;
    }

}
