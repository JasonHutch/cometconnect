package com.example.jasonhutchinson.cometconnect;

import java.util.Map;

/**
 * Created by Jason on 12/7/2017.
 */

public class Organization {

    public String name;
    public String description;
    public String contact; //holds info for president and advisor
    public String socialMedia;
    public String orgID;
    public String institution;
    public Map<String, User> orgMembers;
    public int numMembers = 0;

            public Organization(String name, String description, String institution){
                this.name = name;
                this.description = description;
                this.institution = institution;

            }
            //GETTER METHODS
            public String getName(){
                return name;
    }
            public String getDescription(){
                return description;
    }
            public String getContact(){
                return contact;
            }
            public String getSocialMedia(){
                return socialMedia;
            }
            public String getOrgID(){return orgID;}

            //SETTER METHODS
            public void setName(String name){
                this.name = name;
            }
            public void setDescription(String description){
                this.description = description;
            }
            public void setContact(String contact){
                this.contact = contact;
            }
            public void setSocialMedia(String socialMedia){
                this.socialMedia = socialMedia;
            }

            //METHODS
            public void addMember(User nUser){
                String userKey = nUser.getUserId();
                orgMembers.put(userKey,nUser);
                this.numMembers++;
            }


}
