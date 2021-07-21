package com.wonderlabz.bank.model;

public class BankAccount {
    private Integer accountNumber;
    private String accountType;
    private double amount;
    private double deposit;
    private double withdraw;
    private double transfers;

    public BankAccount(Integer accountNumber, String accountType, double amount, double deposit, double withdraw, double transfers) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.amount = amount;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.transfers = transfers;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }

    public double getTransfers() {
        return transfers;
    }

    public void setTransfers(double transfers) {
        this.transfers = transfers;
    }
}
