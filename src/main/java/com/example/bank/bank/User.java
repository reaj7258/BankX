package com.example.bank.bank;

public class User {
    private String account, name;
    private int balance, amount;
    private boolean req;

    public User(String account, String name, int balance, boolean req, int amount) {
        this.account = account;
        this.name = name;
        this.balance = balance;
        this.req = req;
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean getReq() {
        return req;
    }

    public void setReq(boolean req) {
        this.req = req;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}