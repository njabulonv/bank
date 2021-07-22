package com.wonderlabz.bank.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @Column(name = "id_Number")
    private String id_Number;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "gender")
    private String gender;

    @Column(name = "password")
    private String password;

    @Column(name = "account_number")
    private String accountNumber;

    @OneToMany(targetEntity=BankAccount.class, cascade = CascadeType.ALL)
    private List<BankAccount> bankAccountList = new ArrayList<>();

    public Client(String name, String surname, String address, String id_Number, String emailAddress, String gender, String password, String accountNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.id_Number = id_Number;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.password = password;
        this.accountNumber = accountNumber;
    }

    public Client() {
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_Number() {
        return id_Number;
    }

    public void setId_Number(String id_Number) {
        this.id_Number = id_Number;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
}
