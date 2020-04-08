package com.example.tema3;

import org.json.JSONException;
import org.json.JSONObject;

class ToDo {
    public int userId;
    public int id;
    public String title;
    public boolean completed;

    public ToDo fromJSON(JSONObject todoJ) throws JSONException {

        userId=todoJ.getInt("userId");
        id=todoJ.getInt("id");
        title=todoJ.getString("title");
        completed=todoJ.getBoolean("completed");

        return this;
    }
    public ToDo(){

    }
}
