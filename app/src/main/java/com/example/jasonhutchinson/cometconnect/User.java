package com.example.jasonhutchinson.cometconnect;

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

    public User(){

    }
    public User(String email,String password,String userId){
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

}
