package com.company;

public class Person {
    String name;
    int birth_Year;

    public Person(String name, int birth_Year) {
        this.name = name;
        this.birth_Year = birth_Year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return birth_Year;
    }

    public void setYear(int year) { this.birth_Year = year; }
}
