package com.example.jasonhutchinson.cometconnect;

/**
 * Created by Jason on 12/7/2017.
 */

public class Organization {

    public String name;
    public String description;
    public String contact; //holds info for president and advisor
    public String socialMedia;
    public String orgID;
    public int numMembers = 0;

            public Organization(String name, String description, String contact, String socialMedia){
                this.name = name;
                this.description = description;
                this.contact = contact;
                this.socialMedia = socialMedia;

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
            public void addMember(){
                this.numMembers++;
            }

}
