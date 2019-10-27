package com.yuditsky.financial_accounting.bean;

import java.io.Serializable;
import java.util.Objects;

public class Payment extends Transaction implements Serializable {
    private PaymentType type;

    public Payment(int id, double amount, PaymentType type) {
        super(id, amount);
        this.type = type;
    }

    public Payment(double amount, PaymentType type) {
        super(amount);
        this.type = type;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Payment payment = (Payment) o;
        return type == payment.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Add{" +
                "type=" + type +
                '}';
    }
}
