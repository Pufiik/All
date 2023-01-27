package ru.pugovishnikova;

public class Person {
    private String number;
    private String surname;

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public Person(String number, String surname) {
        this.number = number;
        this.surname = surname;
    }
}
