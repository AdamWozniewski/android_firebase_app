package com.adamwozniewski.android_firebase_test.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.adamwozniewski.android_firebase_test.Models.Pong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PongService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!intent.hasExtra("receiver")) {
            return super.onStartCommand(intent, flags,startId);
        }
        final String receiver = intent.getStringExtra("receiver");
        FirebaseDatabase.getInstance().getReference("users").child(receiver).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // nasłuchiwanie na zmiane
                if (dataSnapshot.exists()) { // sprawdza czy dana osoba istneije
                    FirebaseDatabase.getInstance().getReference("pongs").child(receiver).push().setValue(new Pong(receiver));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
