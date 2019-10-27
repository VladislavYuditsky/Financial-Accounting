package com.yuditsky.financial_accounting.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private boolean authorized;

    public User() {
    }

    public User(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return authorized == user.authorized;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorized);
    }

    @Override
    public String toString() {
        return "User{" +
                "authorized=" + authorized +
                '}';
    }
}
