package com.yuditsky.financial_accounting.bean;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    private int id; //util.TransactionGenerator - service(singleton) + dao lastId() / initialize - ServiceFactory /
    private double amount;

    protected Transaction(double amount) {
        //this.id = id;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0;
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }*/

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
