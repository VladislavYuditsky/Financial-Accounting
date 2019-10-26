package com.yuditsky.financial_accounting.dao.impl;

import com.yuditsky.financial_accounting.bean.Payment;
import com.yuditsky.financial_accounting.bean.Payroll;
import com.yuditsky.financial_accounting.bean.Transaction;
import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.TransactionDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTransactionDAO implements TransactionDAO {
    private static final String DATA_FILE_PATH = "resources/user.txt";
    private static final int FIRST_TRANSACTION_POSITION = 3;

    private String parseString(Transaction transaction) {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("\n" + transaction.getId() + " ");

        if (transaction.getClass() == Payment.class) {
            stringBuffer.append("-");
        } else {
            stringBuffer.append("+");
        }

        stringBuffer.append(transaction.getAmount() + " ");

        if (transaction.getClass() == Payment.class) {
            stringBuffer.append(((Payment) transaction).getType());
        } else {
            stringBuffer.append(((Payroll) transaction).getType());
        }

        return String.valueOf(stringBuffer);
    }

    @Override
    public List<Transaction> readTransactions() throws DAOException {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            int i;

            for (i = 0; i < FIRST_TRANSACTION_POSITION; i++) {
                bufferedReader.readLine();
            }

            String buffer;

            while ((buffer = bufferedReader.readLine()) != null) {
                //  transactions.add(buffer);
            }

        } catch (FileNotFoundException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }

        // return String.valueOf(transactions);
        return null;
    }

    @Override
    public void add(Transaction transaction) throws DAOException {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true))) {

            bufferedWriter.write(parseString(transaction));

        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void edit() throws DAOException {
    }

    @Override
    public void delete() throws DAOException {
    }
}
