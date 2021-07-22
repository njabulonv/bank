package com.wonderlabz.bank.model;

import javax.persistence.*;

@Entity(name="BANK_ACCOUNT")
public class BankAccount {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "deposit")
    private double deposit;

    @Column(name = "withdraw")
    private double withdraw;

    @Column(name = "transfers")
    private double transfers;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public BankAccount(String accountNumber, String accountType, double amount, double deposit, double withdraw, double transfers) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.amount = amount;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.transfers = transfers;
    }

    public BankAccount(String accountNumber, String accountType, double amount) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.amount = amount;
    }

    public BankAccount() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
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
