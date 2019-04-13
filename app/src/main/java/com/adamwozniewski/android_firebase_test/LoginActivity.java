package com.adamwozniewski.android_firebase_test;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adamwozniewski.android_firebase_test.Models.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail;
    EditText editTextPassword;
    Button loginButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextEmail = (EditText) findViewById(R.id.email);
        this.editTextPassword = (EditText) findViewById(R.id.password);
        this.loginButton = (Button) findViewById(R.id.loginButton);

        this.loginButton.setOnClickListener(this); // this to odwołanie do metody onClick

        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        final String password = this.editTextPassword.getText().toString();
        final String email = this.editTextEmail.getText().toString();
        if (
                password.trim().length() > 0
                && email.trim().length() > 0
        ) {
            this.firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Logowanie udane", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Błąd: " + task.getException(), Toast.LENGTH_SHORT).show();
                        if (task.getException().getMessage().contains("There is no user record")) {
                            LoginActivity.this.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(LoginActivity.this, "Rejestracja udana", Toast.LENGTH_SHORT).show();
                                    LoginActivity.this.userLoggedIn(email);
                                }
                            });
                        }
                    }
                }
            }); // metoda onComplete
        }
    }

    private void userLoggedIn(String email) {
        String name = email.split("@")[0];
        FirebaseDatabase.getInstance().getReference("users").child(name).setValue(new Person(name, FirebaseAuth.getInstance().getCurrentUser().getUid())).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(intent);
        LoginActivity.this.finish();
    }
}
