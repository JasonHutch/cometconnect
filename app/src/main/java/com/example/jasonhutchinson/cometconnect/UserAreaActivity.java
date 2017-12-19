package com.example.jasonhutchinson.cometconnect;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

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
    public String[] lstItems = {"NSBE", "ISO","USS"};
    ImageButton addOrg;
    Toolbar toolbar;
    MaterialSearchView searchView;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_area_activity);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        addOrg = (ImageButton) findViewById(R.id.addNewOrgButton);
        addOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAreaActivity.this, Create_org.class);
                UserAreaActivity.this.startActivity(intent);
            }
        });
        getDate();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Material Search");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        lstView = (ListView) findViewById(R.id.lstview);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lstItems);
        lstView.setAdapter(adapter);

       searchView = (MaterialSearchView) findViewById(R.id.search_view);

       searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
           @Override
           public void onSearchViewShown() {

           }

           @Override
           public void onSearchViewClosed() {
               lstView = (ListView) findViewById(R.id.lstview);
               ArrayAdapter adapter = new ArrayAdapter(UserAreaActivity.this,android.R.layout.simple_list_item_1,lstItems);
               lstView.setAdapter(adapter);
           }
       });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<String> lstFound = new ArrayList<String>();
                    for (String item : lstItems) {
                        if (item.contains(newText))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(UserAreaActivity.this, android.R.layout.simple_list_item_1, lstFound);
                    lstView.setAdapter(adapter);
                }
                    else{
                        ArrayAdapter adapter = new ArrayAdapter(UserAreaActivity.this, android.R.layout.simple_list_item_1, lstItems);
                        lstView.setAdapter(adapter);
                    }
                    return true;
            }

        });

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

        //SEARCH FUNCTIONALITY


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
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


