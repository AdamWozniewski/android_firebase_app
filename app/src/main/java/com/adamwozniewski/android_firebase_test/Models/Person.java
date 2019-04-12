package com.adamwozniewski.android_firebase_test.Models;

public class Person {
    private String email, id;

    public Person() {}

    public Person(String email, String id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }
}
