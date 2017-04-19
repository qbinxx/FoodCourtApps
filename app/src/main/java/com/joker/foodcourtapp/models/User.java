package com.joker.foodcourtapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rick on 08/08/16.
 */
public class User {

    private String username;
    private String userslug;
    private String picture;
    private int uid;
    private String iconText;
    private String iconBgColor;

    private long joindate;
    private long lastonline;
    private int reputation;
    private int postcount;
    private boolean banned;
    private String status;
    private boolean confirmationStatus;
    private boolean administrator;

    private String email;
    private String fullName;
    private String location;
    private String website;
    private String birthday;
//    private Timestamp joinDateISO;
//    private Timestamp lastOnlineIso;

    public User() {
    }

    // Constructor sebagian
    public User(String username, String userslug, String picture, int uid, String iconText, String iconBgColor) {
        this.username = username;
        this.userslug = userslug;
        this.picture = picture;
        this.uid = uid;
        this.iconText = iconText;
        this.iconBgColor = iconBgColor;
    }

    // Constructor penuh
    public User(String username, String userslug, String picture, int uid, String iconText, String iconBgColor, long joindate, long lastonline, int reputation, int postcount, boolean banned, String status, boolean confirmationStatus, boolean administrator,String email,String fullName,String website,String location,String birthday) {
        this.username = username;
        this.userslug = userslug;
        this.picture = picture;
        this.uid = uid;
        this.iconText = iconText;
        this.iconBgColor = iconBgColor;
        this.joindate = joindate;
        this.lastonline = lastonline;
        this.reputation = reputation;
        this.postcount = postcount;
        this.banned = banned;
        this.status = status;
        this.confirmationStatus = confirmationStatus;
        this.administrator = administrator;
        this.email = email;
        this.fullName = fullName;
        this.location = location;
        this.website = website;
        this.birthday = birthday;
    }


    // Constructor untuk konversi dari json ke java class
    public User(JSONObject object){
        try {
            this.username = object.getString("username");
            this.userslug = object.getString("userslug");
            this.picture = object.getString("picture");
            this.uid = object.getInt("uid");
            this.iconText = object.getString("icon:text");
            this.iconBgColor = object.getString("icon:bgColor");
            this.joindate = object.getLong("joindate");
            this.lastonline = object.getLong("lastonline");
            this.reputation = object.getInt("reputation");
            this.postcount = object.getInt("postcount");
            this.banned = object.getBoolean("banned");
            this.status = object.getString("status");
            this.confirmationStatus = object.getBoolean("email:confirmed");
            this.administrator = object.getBoolean("administrator");
            this.email = object.getString("email");
            this.fullName = object.getString("fullname");
            this.location = object.getString("location");
            this.website = object.getString("website");
            this.birthday = object.getString("birthday");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method untuk array of Json Object ke list of object
    // Category.fromJson(jsonArray);
    public static ArrayList<User> fromJson(JSONArray jsonObjects) {
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                users.add(new User(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserslug() {
        return userslug;
    }

    public void setUserslug(String userslug) {
        this.userslug = userslug;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getIconText() {
        return iconText;
    }

    public void setIconText(String iconText) {
        this.iconText = iconText;
    }

    public String getIconBgColor() {
        return iconBgColor;
    }

    public void setIconBgColor(String iconBgColor) {
        this.iconBgColor = iconBgColor;
    }


    public long getJoindate() {
        return joindate;
    }

    public void setJoindate(long joindate) {
        this.joindate = joindate;
    }

    public long getLastonline() {
        return lastonline;
    }

    public void setLastonline(long lastonline) {
        this.lastonline = lastonline;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public int getPostcount() {
        return postcount;
    }

    public void setPostcount(int postcount) {
        this.postcount = postcount;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(boolean confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
