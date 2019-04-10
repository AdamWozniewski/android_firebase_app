package com.adamwozniewski.android_firebase_test.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private String name;

//    @Exclude // wyklucza ten element z bazy
//    private ArrayList<Person> friends = new ArrayList<>();

//    @PropertyName("friends") szukaj w friends, mimo innej nazwy zmiennej
//    public Map<String, Object> friendsHashMap = new HashMap<>();

    public Map<String, Object> friends = new HashMap<>();

    public Person(String name) {
        this.name = name;
    }
    public Person() {}

    public String getName() {
        return this.name;
    }

//    public ArrayList<Person> getFriends() {
//        return this.friends;
//    }

    public void saveFriendsToDB(DatabaseReference databaseReference) { // zalecany zapis do bazy
//        for (Person friend : this.friends) {
//            databaseReference.child("friends").push().setValue(friend);
//        }
    }

}
