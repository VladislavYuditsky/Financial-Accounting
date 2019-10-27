package com.yuditsky.financial_accounting.service.util;

import com.yuditsky.financial_accounting.bean.*;

import static com.yuditsky.financial_accounting.service.util.Constants.EMPTY_STRING;
import static com.yuditsky.financial_accounting.service.util.Constants.PARAM_DELIMITER;

public class Parser {

    private static final Parser instance = new Parser();

    private Parser() {
    }

    public static Parser getInstance() {
        return instance;
    }

    public String parseString(Transaction transaction) {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(transaction.getId() + PARAM_DELIMITER);

        if (transaction.getClass() == Payment.class) {
            stringBuffer.append(Payment.class.getSimpleName().toLowerCase() + PARAM_DELIMITER);
        } else {
            stringBuffer.append(Payroll.class.getSimpleName().toLowerCase() + PARAM_DELIMITER);
        }

        stringBuffer.append(transaction.getAmount() + PARAM_DELIMITER);

        if (transaction.getClass() == Payment.class) {
            stringBuffer.append(((Payment) transaction).getType());
        } else {
            stringBuffer.append(((Payroll) transaction).getType());
        }

        return String.valueOf(stringBuffer);
    }

    public Transaction parseTransaction(String buffer) {

        String strId = buffer.substring(0, buffer.indexOf(PARAM_DELIMITER));
        int id = Integer.parseInt(strId);

        buffer = buffer.replaceFirst(strId, EMPTY_STRING);
        buffer = buffer.replaceFirst(PARAM_DELIMITER, EMPTY_STRING);

        String transactionType = buffer.substring(0, buffer.indexOf(PARAM_DELIMITER));

        buffer = buffer.replaceFirst(transactionType, EMPTY_STRING);
        buffer = buffer.replaceFirst(PARAM_DELIMITER, EMPTY_STRING);

        String strAmount = buffer.substring(0, buffer.indexOf(PARAM_DELIMITER));
        Double amount = Double.parseDouble(strAmount);

        buffer = buffer.replaceFirst(strAmount, EMPTY_STRING);
        buffer = buffer.replaceFirst(PARAM_DELIMITER, EMPTY_STRING);

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
}
