package com.example.jasonhutchinson.cometconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Jason on 12/8/2017.
 */

public class Create_org extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    ProgressDialog progressDialog;
    Button register_Org;
    EditText etOrgName;
    EditText etOrgInstitution;
    EditText etOrgDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_org);

        progressDialog = new ProgressDialog(this);
        register_Org = (Button) findViewById(R.id.org_registerButton);
        etOrgName = (EditText) findViewById(R.id.org_name);
        etOrgInstitution = (EditText) findViewById(R.id.org_institution);
        etOrgDescription = (EditText) findViewById(R.id.org_description);

        register_Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_Org();
            }
        });


    }

    public void register_Org(){
        String name = etOrgName.getText().toString().trim();
        String institution = etOrgInstitution.getText().toString().trim();
        String description = etOrgDescription.getText().toString().trim();

        Organization new_Org = new Organization(name,description,institution);
        mRootRef.child("Institutions").child(institution).child("Organizations").child(name).setValue(new_Org);
        Intent intent = new Intent(Create_org.this,UserAreaActivity.class);
        Create_org.this.startActivity(intent);

    }
}
