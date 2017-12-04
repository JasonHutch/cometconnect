package com.example.jasonhutchinson.cometconnect;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason Hutchinson on 11/19/2017.
 */

public class Register_Request extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://distinctocula.com/Register.php";
    private Map<String,String> params;

    public Register_Request(String Name, String Username, String Major, String Password, String Email, Response.Listener<String> listner){
        super(Method.POST, REGISTER_REQUEST_URL,listner,null);
        params = new HashMap<>();
        params.put("Name",Name);
        params.put("Username",Username);
        params.put("Major",Major);
        params.put("Password",Password);
        params.put("Email",Email);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
