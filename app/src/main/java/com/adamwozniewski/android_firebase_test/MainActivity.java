package com.adamwozniewski.android_firebase_test;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.adamwozniewski.android_firebase_test.Models.AdapterPong;
import com.adamwozniewski.android_firebase_test.Models.Pong;
import com.adamwozniewski.android_firebase_test.Services.PongService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements ChildEventListener {
    AdapterPong adapterPong;
    EditText editText, messageText;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = (EditText) findViewById(R.id.editText);
        this.messageText = (EditText) findViewById(R.id.editTextContent); // pole do wpisania wiadomosci
        this.floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView); // to taki komponent

        this.adapterPong = new AdapterPong();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setAdapter(this.adapterPong);

        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiver = MainActivity.this.editText.getText().toString().trim();
                String message = MainActivity.this.messageText.getText().toString();
                if (receiver.length() > 0) {
                    Intent serviceIntent = new Intent(MainActivity.this, PongService.class);
                    serviceIntent.putExtra("receiver", receiver);
                    serviceIntent.putExtra("message", message);
                    startService(serviceIntent);
                }
            }
        });
//        this.checkIfPersonIsLoggedIn();

        redirectToLogin();
    }

//    @Override
//    protected void onResume() { // na kazde uruchomienie aplikacji
//        super.onResume();
//        checkIfPersonIsLoggedIn();
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (this.databaseReference != null) {
//            this.databaseReference.removeEventListener(this);
//        }
//    }

//    private void checkIfPersonIsLoggedIn() {
//        if(FirebaseAuth.getInstance().getCurrentUser() == null) { // sprawdzamy czy ktoś jest zalogowany
//            redirectToLogin();
//        } else {
//            this.databaseReference = FirebaseDatabase.getInstance()
//                    .getReference("pongs")
//                    .child(FirebaseAuth
//                            .getInstance()
//                            .getCurrentUser()
//                            .getEmail()
//                            .split("@")[0]);
//            this.databaseReference.addChildEventListener(this);
//        }
//    }

    private void redirectToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Uruchom Login Actibvity
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            this.redirectToLogin();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        if (this.adapterPong != null) {
            Pong pong = dataSnapshot.getValue(Pong.class);
            this.adapterPong.getPongs().add(pong);
            this.adapterPong.notifyDataSetChanged();
        }
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}



