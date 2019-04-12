package com.adamwozniewski.android_firebase_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() { // na kazde uruchomienie aplikacji
        super.onResume();
        if(FirebaseAuth.getInstance().getCurrentUser() == null) { // sprawdzamy czy ktoś jest zalogowany
            Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Uruchom Login Actibvity
            MainActivity.this.startActivity(intent);
            MainActivity.this.finish();
        }
    }
}
