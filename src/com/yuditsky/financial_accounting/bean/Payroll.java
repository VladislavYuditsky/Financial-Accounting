package com.yuditsky.financial_accounting.bean;

import java.io.Serializable;
import java.util.Objects;

public class Payroll extends Transaction implements Serializable {
    private PayrollType type;

    public Payroll(double amount, PayrollType type) {
        super(amount);
        this.type = type;
    }

    public Payroll(int id, double amount, PayrollType type) {
        super(id, amount);
        this.type = type;
    }

    public PayrollType getType() {
        return type;
    }

    public void setType(PayrollType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Payroll payroll = (Payroll) o;
        return type == payroll.type;
    }

    @Override
    public int hashCode() { ///////////////////////
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "AddPayroll{" +
                "type=" + type +
                '}';
    }
}
