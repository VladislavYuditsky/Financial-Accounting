package com.yuditsky.financial_accounting.dao.impl;

import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.TransactionDAO;

import java.io.*;

public class FileTransactionDAO implements TransactionDAO {
    private static final String DATA_FILE_PATH = "resources/user.txt";
    private static final int FIRST_TRANSACTION_POSITION = 3;

    @Override
    public String readTransactions() throws DAOException {
        StringBuffer transactions = new StringBuffer();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            int i;

            for (i = 0; i < FIRST_TRANSACTION_POSITION; i++) {
                bufferedReader.readLine();
            }

            String buffer;

            while ((buffer = bufferedReader.readLine()) != null) {
                transactions.append(buffer);
                transactions.append("\n");
            }

        } catch (FileNotFoundException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return String.valueOf(transactions);
    }

    @Override
    public void add(String transaction) throws DAOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true))) {

            bufferedWriter.write(transaction);

        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

        @Override
        public void edit () throws DAOException {
        }

        @Override
        public void delete () throws DAOException {
        }
    }
