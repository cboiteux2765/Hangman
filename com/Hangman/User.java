package com.Hangman;

public class User {
    private String name;
    private String date;
    private int tries;

    public User(String name, String date, int tries) {
        this.name = name;
        this.date = date;
        this.tries = tries;
    }

    public String toString() {
        return "Name: " + this.name + ", Date Achieved: " + this.date + ", Tries: " + this.tries;
    }
}
