package com.yuditsky.financial_accounting.dao.impl;

import com.yuditsky.financial_accounting.bean.*;
import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.TransactionDAO;
import com.yuditsky.financial_accounting.service.util.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.yuditsky.financial_accounting.service.util.Constants.*;

public class FileTransactionDAO implements TransactionDAO {

    Parser parser = Parser.getInstance();

    public int find(int id) throws DAOException {

        List<Transaction> transactions = readTransactions();
        int stringNumber = -1;

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                stringNumber = i;
                break;
            }
        }

        return stringNumber;
    }

    @Override
    public List<Transaction> readTransactions() throws DAOException {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {

            String buffer;

            while ((buffer = bufferedReader.readLine()) != null) {
                if (!buffer.equals(EMPTY_STRING)) {
                    transactions.add(parser.parseTransaction(buffer));
                } else {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return transactions;
    }

    @Override
    public void add(Transaction transaction) throws DAOException {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true))) {

            bufferedWriter.write(parser.parseString(transaction) + NEW_LINE);

        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Transaction read(int id) throws DAOException {

        Transaction transaction = null;
        int stringNumber;

        if ((stringNumber = find(id)) >= 0) {

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {

                String buffer = null;

                for (int i = 0; i < stringNumber + 1; i++) {
                    buffer = bufferedReader.readLine();
                }

                transaction = parser.parseTransaction(buffer);

            } catch (FileNotFoundException e) {
                throw new DAOException(e.getMessage(), e);
            } catch (IOException e) {
                throw new DAOException(e.getMessage(), e);
            }
        }

        return transaction;
    }

    @Override
    public boolean replace(int id, Transaction transaction) throws DAOException {

        int stringNumber;

        if ((stringNumber = find(id)) >= 0) {
            File tmp;

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH));
                 BufferedWriter bufferedWriter
                         = new BufferedWriter(new FileWriter(tmp = File.createTempFile("tmp", "")))) {

                for (int i = 0; i < stringNumber; i++) {
                    bufferedWriter.write(String.format("%s%n", bufferedReader.readLine()));
                }

                bufferedReader.readLine();
                bufferedWriter.write(parser.parseString(transaction) + "\n");

                String buffer;
                while ((buffer = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(String.format("%s%n", buffer));
                }

            } catch (IOException e) {
                throw new DAOException(e.getMessage(), e);
            }

            File oldFile = new File(DATA_FILE_PATH);
            if (oldFile.delete())
                tmp.renameTo(oldFile);

        } else {
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {

        int stringNumber;

        if ((stringNumber = find(id)) >= 0) {

            try (RandomAccessFile raf = new RandomAccessFile(DATA_FILE_PATH, "rw")) {

                for (int i = 0; i < stringNumber; i++) {
                    raf.readLine();
                }

                long writePosition = raf.getFilePointer();
                raf.readLine();

                long readPosition = raf.getFilePointer();

                raf.seek(readPosition);

                byte[] buffer = new byte[1024];
                int bytesNumber;
                while ((bytesNumber = raf.read(buffer)) != -1) {
                    raf.seek(writePosition);
                    raf.write(buffer, 0, bytesNumber);
                    readPosition += bytesNumber;
                    writePosition += bytesNumber;
                    raf.seek(readPosition);
                }

                raf.setLength(writePosition);
            } catch (FileNotFoundException e) {
                throw new DAOException(e.getMessage(), e);
            } catch (IOException e) {
                throw new DAOException(e.getMessage(), e);
            }
        } else {
            return false;
        }

        return true;
    }
}
