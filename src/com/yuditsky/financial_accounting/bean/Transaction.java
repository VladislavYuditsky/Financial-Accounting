package com.yuditsky.financial_accounting.bean;

public abstract class Transaction { //надо ли гет/сет/Override, asbstract в bean&
    private int id; //util.TransactionGenerator - service(singleton) + dao lastId() / initialize - ServiceFactory /
    private double amount;
}
