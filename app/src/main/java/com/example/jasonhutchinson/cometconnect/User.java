package com.example.jasonhutchinson.cometconnect;

import java.util.Map;

/**
 * Created by Hani on 12/4/2017.
 */

public class User {

    public String firstname;
    public String lastname;
    public String username;
    public String email;
    public String password;
    public String major;
    public String userId;
    public String institution;
    public Map<String, Organization> currentOrgs;

    public User(){

    }
    public User(String email,String password,String userId){
        this.email = email;
        this.password = password;
        this.userId = userId;
    }
    //GETTERS
    public String getUserId(){
        return userId;
    }

    //SETTERS


    //METHODS
    public void org_Subscribe(Organization nOrg){
        String orgKey = nOrg.getOrgID();
        currentOrgs.put(orgKey,nOrg);
    }
}
