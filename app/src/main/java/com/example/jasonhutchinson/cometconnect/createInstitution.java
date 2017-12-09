package com.example.jasonhutchinson.cometconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Jason on 12/8/2017.
 */

public class createInstitution extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference institutionRef = mRootRef.child("Institutions");
    ProgressDialog progressDialog;
    Button register;
    EditText etInstitution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_institution);
        register = (Button) findViewById(R.id.instRegButton);
        etInstitution = (EditText) findViewById(R.id.institution_new);
        progressDialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_Institution();
            }
        });


    }


    private void register_Institution(){


        String institution = etInstitution.getText().toString().trim();
        Instittution inst = new Instittution(institution);
        institutionRef.child(institution).setValue(inst); // create a new user child with email, and set node to new user
        progressDialog.setMessage("Registering Institution...");
        progressDialog.show();

        Intent intent =  new Intent(createInstitution.this, MainActivity.class);
        createInstitution.this.startActivity(intent);



    }
}