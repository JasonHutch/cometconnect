package com.example.jasonhutchinson.cometconnect;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserAreaActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    ImageButton addOrg;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_area_activity);
        addOrg = (ImageButton) findViewById(R.id.addNewOrgButton);
        addOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAreaActivity.this, Create_org.class);
                UserAreaActivity.this.startActivity(intent);
            }
        });
        getDate();

        //final EditText etusername = (EditText) findViewById(R.id.etUsername);
        //final EditText etName = (EditText) findViewById(R.id.etName);
        //final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);


        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String username = intent.getStringExtra("Username");

        String message = name + " Welcome to Commet Connect";
        //welcomeMessage.setText(message);
        //etusername.setText(username);
        //

}

public void getDate() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat mDateFormat = new SimpleDateFormat("EEEE\nLLL d, yyyy");
    String strDate = mDateFormat.format(calendar.getTime());
    display(strDate);
}

private void display(String num){
        TextView textview = (TextView) findViewById(R.id.current_date);
        textview.setText(num);
}


}


