package com.example.jasonhutchinson.cometconnect;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason Hutchinson on 11/21/2017.
 */

public class Login_Request extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://distinctocula.com/Login.php";
    private Map<String,String> params;

    public Login_Request(String Username, String Password, Response.Listener<String> listner){
        super(Request.Method.POST, LOGIN_REQUEST_URL,listner,null);
        params = new HashMap<>();
        params.put("Username",Username);
        params.put("Password",Password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


