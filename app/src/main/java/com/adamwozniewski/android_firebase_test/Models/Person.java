package com.adamwozniewski.android_firebase_test.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Person {
    private String name;

//    @Exclude // wyklucza ten element z bazy
    private ArrayList<Person> friends = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Person> getFriends() {
        return this.friends;
    }

    public void saveFriendsToDB(DatabaseReference databaseReference) { // zalecany zapis do bazy
        for (Person friend : this.friends) {
            databaseReference.child("friends").push().setValue(friend);
        }
    }

}
