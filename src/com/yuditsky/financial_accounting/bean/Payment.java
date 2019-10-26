package com.yuditsky.financial_accounting.bean;

import java.util.Objects;

public class Payment extends Transaction { //надо ли имплементить сериализацию?
    private PaymentType type;

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

    @Override //Seriall
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "AddPayment{" +
                "type=" + type +
                '}';
    }
}
