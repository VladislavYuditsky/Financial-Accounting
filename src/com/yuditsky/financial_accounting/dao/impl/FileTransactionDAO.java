package com.yuditsky.financial_accounting.dao.impl;

import com.yuditsky.financial_accounting.bean.*;
import com.yuditsky.financial_accounting.dao.DAOException;
import com.yuditsky.financial_accounting.dao.TransactionDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.yuditsky.financial_accounting.service.util.Constants.DATA_FILE_PATH;
import static com.yuditsky.financial_accounting.service.util.Constants.PARAM_DELIMITER;

public class FileTransactionDAO implements TransactionDAO {

    private String parseString(Transaction transaction) { //парсеры в логику

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(transaction.getId() + " ");

        if (transaction.getClass() == Payment.class) {
            stringBuffer.append(Payment.class.getSimpleName().toLowerCase() + " ");
        } else {
            stringBuffer.append(Payroll.class.getSimpleName().toLowerCase() + " ");
        }

        stringBuffer.append(transaction.getAmount() + " ");

        if (transaction.getClass() == Payment.class) {
            stringBuffer.append(((Payment) transaction).getType());
        } else {
            stringBuffer.append(((Payroll) transaction).getType());
        }

        return String.valueOf(stringBuffer);
    }

    private Transaction parseTransaction(String buffer) {

        String strId = buffer.substring(0, buffer.indexOf(PARAM_DELIMITER));
        int id = Integer.parseInt(strId);

        buffer = buffer.replaceFirst(strId, "");
        buffer = buffer.replaceFirst(" ", "");

        String transactionType = buffer.substring(0, buffer.indexOf(PARAM_DELIMITER));

        buffer = buffer.replaceFirst(transactionType, "");
        buffer = buffer.replaceFirst(" ", "");

        String strAmount = buffer.substring(0, buffer.indexOf(PARAM_DELIMITER));
        Double amount = Double.parseDouble(strAmount);

        buffer = buffer.replaceFirst(strAmount, "");
        buffer = buffer.replaceFirst(" ", "");

        Transaction transaction;

        if (transactionType.equals(Payment.class.getSimpleName().toLowerCase())) {
            PaymentType type = PaymentType.valueOf(buffer);
            transaction = new Payment(id, amount, type);
        } else {
            PayrollType type = PayrollType.valueOf(buffer);
            transaction = new Payroll(id, amount, type);
        }

        return transaction;
    }

    @Override
    public List<Transaction> readTransactions() throws DAOException {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {

            String buffer;

            while ((buffer = bufferedReader.readLine()) != null) {
                transactions.add(parseTransaction(buffer));
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

            bufferedWriter.write(parseString(transaction) + "\n");

        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Transaction read(int id) throws DAOException {

        List<Transaction> transactions = readTransactions();
        int stringNumber = -1;

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                stringNumber = i;
                break;
            }
        }

        Transaction transaction = null;

        if (stringNumber >= 0) {

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {

                String buffer = null;

                for (int i = 0; i < stringNumber + 1; i++) {
                    buffer = bufferedReader.readLine();
                }

                transaction = parseTransaction(buffer);

            } catch (FileNotFoundException e) {
                throw new DAOException(e.getMessage(), e);
            } catch (IOException e) {
                throw new DAOException(e.getMessage(), e);
            }
        }

        return transaction;
    }

    @Override
    public boolean replace(int id, Transaction transaction) throws DAOException { //вынести проверку имеется ли строка с заданным ID
        List<Transaction> transactions = readTransactions();
        int stringNumber = -1;

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                stringNumber = i;
                break;
            }
        }

        if (stringNumber >= 0) {
            File tmp = null;

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH));
                 BufferedWriter bufferedWriter
                         = new BufferedWriter(new FileWriter(tmp = File.createTempFile("tmp", "")))) {

                for (int i = 0; i < stringNumber; i++) {
                    bufferedWriter.write(String.format("%s%n", bufferedReader.readLine()));
                }

                bufferedReader.readLine();
                bufferedWriter.write(parseString(transaction) + "\n");

                String buffer;
                while ((buffer = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(String.format("%s%n", buffer));
                }

            } catch (IOException e) {
                e.printStackTrace();
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

        List<Transaction> transactions = readTransactions();
        int stringNumber = -1;

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                stringNumber = i;
                break;
            }
        }

        if (stringNumber >= 0) {

            try (RandomAccessFile raf = new RandomAccessFile(DATA_FILE_PATH, "rw")) {

                long writePosition = raf.getFilePointer();
                raf.readLine();

                long readPosition = raf.getFilePointer();

                boolean lastString = false;
                if (raf.readLine() == null) {
                    lastString = true;
                }

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

                if (lastString) {
                    raf.setLength(writePosition - 1);
                } else {
                    raf.setLength(writePosition);
                }
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
