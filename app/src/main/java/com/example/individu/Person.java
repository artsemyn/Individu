package com.example.individu;

public class Person {
    private String name;
    private String number;
    private String email;
    private String address;

    public Person(String name, String number, String email, String address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
