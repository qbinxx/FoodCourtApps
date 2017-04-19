package com.joker.foodcourtapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rick on 08/08/16.
 */
public class Topic {

   private int tid;
   private int uid;
   private int cid;
   private String title;
   private String slug;
   private int postCount;
   private int deleted;

    private Long timeStamp;

    public Topic() {
    }

    public Topic(int tid, int uid, int cid, String title, String slug, int postCount, int deleted) {
        this.tid = tid;
        this.uid = uid;
        this.cid = cid;
        this.title = title;
        this.slug = slug;
        this.postCount = postCount;
        this.deleted = deleted;
    }

    // Constructor untuk konversi dari json ke java class
    public Topic(JSONObject object){
        try {
            this.tid = object.getInt("tid");
            this.uid = object.getInt("uid");
            this.cid = object.getInt("cid");
            this.title = object.getString("title");
            this.slug = object.getString("slug");
            this.postCount = object.getInt("postCount");
            this.deleted = object.getInt("deleted");
            this.timeStamp = object.getLong("timestamp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method untuk array of Json Object ke list of object
    // Category.fromJson(jsonArray);
    public static ArrayList<Topic> fromJson(JSONArray jsonObjects) {
        ArrayList<Topic> topics = new ArrayList<Topic>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                topics.add(new Topic(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return topics;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }


    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
