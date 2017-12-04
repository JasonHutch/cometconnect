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
    private String URL_FEED = "https://api.androidhive.info/feed/feed.json";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_area_activity);
        getDate();
        createFeed();

        //final EditText etusername = (EditText) findViewById(R.id.etUsername);
        //final EditText etName = (EditText) findViewById(R.id.etName);
        //final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);


        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String username = intent.getStringExtra("Username");

        String message = name + " Welcome to Commet Connect";
        //welcomeMessage.setText(message);
        //etusername.setText(username);
        //etName.setText(name);
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

private void createFeed(){

    listView = (ListView) findViewById(R.id.list);

    feedItems = new ArrayList<FeedItem>();

    listAdapter = new FeedListAdapter(this, feedItems);
    listView.setAdapter(listAdapter);

    // These two lines not needed,
    // just to get the look of facebook (changing background color & hiding the icon)
    getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
    getActionBar().setIcon(
            new ColorDrawable(getResources().getColor(android.R.color.transparent)));

    // We first check for cached request
    Cache cache = AppController.getInstance().getRequestQueue().getCache();
    Cache.Entry entry = cache.get(URL_FEED);
    if (entry != null) {
        // fetch the data from cache
        try {
            String data = new String(entry.data, "UTF-8");
            try {
                parseJsonFeed(new JSONObject(data));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    } else {
        // making fresh volley request and getting json
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                URL_FEED, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    parseJsonFeed(response);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

}

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R, menu);
        return true;
    }
}


