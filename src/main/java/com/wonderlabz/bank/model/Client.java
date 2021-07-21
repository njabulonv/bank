package com.wonderlabz.bank.model;

public class Client {
    private String name;
    private String surname;
    private String address;
    private String id_Number;
    private String emailAddress;
    private String gender;
    private String password;

    public Client(String name, String surname, String address, String id_Number, String emailAddress, String gender, String password) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.id_Number = id_Number;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.password = password;
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
}
