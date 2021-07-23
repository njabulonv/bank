package com.wonderlabz.bank.model;

import javax.persistence.*;

import java.util.Date;

@Entity(name="BANK_STATEMENT")
public class BankStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bankstatement_id")
    private int bankStatementId;

    @Column(name = "clientName")
    private String clientName;

    @Column(name = "clientSurname")
    private String clientSurname;

    @Column(name = "bank_date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "transfers")
    private double transfers;

    public BankStatement(String clientName, String clientSurname, Date date, String description, String transactionType, String accountNumber) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.date = date;
        this.description = description;
        this.transactionType = transactionType;
        this.accountNumber = accountNumber;
    }

    public BankStatement(Date date, String description, String transactionType, String accountNumber) {
        this.date = date;
        this.description = description;
        this.transactionType = transactionType;
        this.accountNumber = accountNumber;
    }
    public BankStatement(Date date, String description, String transactionType, String accountNumber, double transfers) {
        this.date = date;
        this.description = description;
        this.transactionType = transactionType;
        this.accountNumber = accountNumber;
        this.transfers = transfers;
    }
    public int getBankStatementId() {  return bankStatementId;   }

    public void setBankStatementId(int bankStatementId) {
        this.bankStatementId = bankStatementId;
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
