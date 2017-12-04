package com.example.jasonhutchinson.cometconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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

public class MainActivity extends AppCompatActivity {

    private VideoView myVideoView;
    private  EditText etEmail;
    private EditText etPassword;
    private ImageButton signin_button;
    private ImageButton register_button;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    TextView greetings_Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //greetings_Text = (TextView) findViewById(R.id.signinmessage); // creates message on sign in page

        //myVideoView = (VideoView) findViewById(R.id.backgroundVideo);
        //Uri uri = Uri.parse("android.resources://"+getPackageName()+"/"+R.raw.milky_way);
        //myVideoView.setVideoURI(uri);
        //myVideoView.start();

        //myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            //@Override
            //public void onPrepared(MediaPlayer mediaPlayer) {
                //mediaPlayer.setLooping(true);
            //}
        //});


         etEmail = (EditText) findViewById(R.id.home_email);
         etPassword = (EditText) findViewById(R.id.home_password);
         signin_button = (ImageButton) findViewById(R.id.home_signin);
         register_button = (ImageButton) findViewById(R.id.home_register);
         progressDialog = new ProgressDialog(this);
         firebaseAuth = FirebaseAuth.getInstance();


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent register_Intent = new Intent (MainActivity.this, create_Account.class);
                MainActivity.this.startActivity(register_Intent);
            }
        });

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                                    // The onclick listner is what the sign in button does when it is pressed

                user_login();
                Response.Listener<String> responseListner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                       /* try {
                            JSONObject jsonResponse = new JSONObject(response);                                        //creates a JSONObject that is used to get data from data field
                            boolean success = jsonResponse.getBoolean("success");                                //.php gives a boolean if the sign in was sucsessful

                            if (success) {                                                                            //if boolean was sucsessful retrieve name and username
                                String name = jsonResponse.getString("Name");
                                String username = jsonResponse.getString("Username");

                                Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);  //Create an Intent for whenever you want to change to a diffent activity
                                intent.putExtra("Name", name);                                                 //pass name variable to the userAreaActvity
                                intent.putExtra("Username", username);                                         // pass username variable to userAreaActivity

                                MainActivity.this.startActivity(intent);                                            //start user area activity


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);   //if login fails send mesage
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                    }
                };

            }
        });

        }

        private void user_login(){

            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();


            progressDialog.setMessage("Signing in..");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if(task.isSuccessful()){
                                //start Profile Activity
                                Intent intent  = new Intent(MainActivity.this,UserAreaActivity.class);
                                MainActivity.this.startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Sign in falied",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });



        }



    }

