package com.adamwozniewski.android_firebase_test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.adamwozniewski.android_firebase_test.Models.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth mAuth;

    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.buttonOk);
        btn.setOnClickListener(this); // this odnosi się do metody OnCLick


        this.firebaseDatabase = FirebaseDatabase.getInstance();

//        this.mAuth = FirebaseAuth.getInstance();
//        this.mAuth.signInAnonymously()
//                .addOnCompleteListener(this)
//                .addOnFailureListener(this);
    }

    @Override
    public void onClick(View v) {
        Person kasia = new Person("Kasia"); // utworzy się model bazy z taką nazwą: Person

        Person tomek = new Person("Tomek"); // znajomi
        Person olga = new Person("Olga");

        kasia.getFriends().add(tomek);
        kasia.getFriends().add(olga);

//        this.firebaseDatabase.getReference() // sposób 1) na zapis do bazy
//                .push()
//                .setValue(kasia) // wartosc, jak jest tablica przyjaciół to pojawi się pole o nazwie 'friends', unikamy ArrayList
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Log.d(TAG, "Udalo sie");
//                    }
//                });

        // SPosob 2) DOBRY
        DatabaseReference databaseReference = this.firebaseDatabase.getReference().child("people").push(); //zalecany sposoób, sam odnajdzie metodę z databaseReference w modelu
        databaseReference.setValue(kasia); // Kasia zostanie zapisana do kolekjci 'people'
        kasia.saveFriendsToDB(databaseReference);

    }
}
