package com.joker.foodcourtapp.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rick on 28/04/17.
 */
public class Tenant {

    private String name;
    private String imgUrl;

    public Tenant() {
    }

    public Tenant(JSONObject object){
        try {
            this.name = object.getString("name");
            this.imgUrl = object.getString("imgUrl");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
