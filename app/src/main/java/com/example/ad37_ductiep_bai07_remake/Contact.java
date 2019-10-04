package com.example.ad37_ductiep_bai07_remake;

import java.io.Serializable;

public class Contact implements Serializable {

    String name;
    String message;
    boolean a;

    public Contact(String name, String message, boolean a) {
        this.name = name;
        this.message = message;
        this.a = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }
}
