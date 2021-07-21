package com.wonderlabz.bank.model;

import java.sql.Date;
import java.sql.Time;

public class BankStatement {

    private String clientName;
    private String clientSurname;
    private Date date;
    private Time time;
    private String description;
    private String transactionType;
    private String accountNumber;
    private double transfers;

    public BankStatement(String clientName, String clientSurname, Date date, Time time, String description, String transactionType, String accountNumber) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.date = date;
        this.time = time;
        this.description = description;
        this.transactionType = transactionType;
        this.accountNumber = accountNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getTransfers() {
        return transfers;
    }

    public void setTransfers(double transfers) {
        this.transfers = transfers;
    }
}
