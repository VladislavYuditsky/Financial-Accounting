package com.yuditsky.financial_accounting.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private boolean authorized;

    public User() {
    }

    public User(String name, boolean authorized) {
        this.name = name;
        this.authorized = authorized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return authorized == user.authorized &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authorized);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", authorized=" + authorized +
                '}';
    }
}
