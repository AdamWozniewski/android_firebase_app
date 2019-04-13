package com.adamwozniewski.android_firebase_test.Models;

import com.google.firebase.auth.FirebaseAuth;

public class Pong {
    private String senderName;
    private String receiverName;

    public String getMessage() {
        return message;
    }

    private String message;
    private long timestamp;

    public Pong(String receiverName) {
        this.receiverName = receiverName;
        this.senderName = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
        this.timestamp = System.currentTimeMillis();
    }
    public Pong(String receiverName, String message) {
        this.receiverName = receiverName;
        this.message = message;
        this.senderName = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
        this.timestamp = System.currentTimeMillis();
    }
    public String getSenderName() {
        return this.senderName;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
