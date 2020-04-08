package com.example.tema3;

import org.json.JSONException;
import org.json.JSONObject;

class User {
    public int id;
    public String name;
    public String username;
    public String email;


    public User fromJSON(JSONObject userJ) throws JSONException{

        id=userJ.getInt("id");
        name=userJ.getString("name");
        username=userJ.getString("username");
        email=userJ.getString("email");

        return this;
    }
    public User(){

    }
}


