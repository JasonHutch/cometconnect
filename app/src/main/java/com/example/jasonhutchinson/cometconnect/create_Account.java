package com.example.jasonhutchinson.cometconnect;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.json.JSONException;
import org.json.JSONObject;

public class create_Account extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); // refrence to root of friebase JSON tree
    DatabaseReference usersRef = mRootRef.child("Users");
    DatabaseReference institutionRef = mRootRef.child("Institution");
    DatabaseReference organizationRef = institutionRef.child("Organizations");
    DatabaseReference conditionRef = mRootRef.child("condition");
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private EditText etemail;
    private EditText etpassword;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        etemail = (EditText) findViewById(R.id.user_email);
        etpassword = (EditText) findViewById(R.id.user_password);



    }

    @Override
    protected void onStart() {


        final ImageButton bRegister = (ImageButton) findViewById(R.id.reg_button);
        super.onStart();
        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //this method is executed everytime condition is updaed in database
                String text = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_User();


            }
        });

    }

    private void register_User(){
        String userID = mRootRef.push().getKey(); //push node generating key  grab key as string
        String email = etemail.getText().toString().trim(); // get email from field, convert to string, and get rid of white space
        String password = etpassword.getText().toString().trim();
        User user = new User(email,password,userID); // create a new user
        usersRef.child(userID).setValue(user); // create a new user child with email, and set node to new user
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(create_Account.this, MainActivity.class);
                        create_Account.this.startActivity(intent);
                    }
                    else{
                        Toast.makeText(create_Account.this,"Could not register try again",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
