package com.joker.foodcourtapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rick on 08/08/16.
 */
public class Post {

    private int pid;
    private int uid;
    private int tid;
    private String content;
    private long timestamp;
    private int deleted;
    private int index;
    private boolean isMainPost;
    private User user;
    private Category category;
    private Topic topic;
    private String timeStampISO;

    public Post() {
    }

    public Post(int pid, int uid, int tid, String content, long timestamp, int deleted, int index, boolean isMainPost, User user, Category category, Topic topic, String timeStampISO) {
        this.pid = pid;
        this.uid = uid;
        this.tid = tid;
        this.content = content;
        this.timestamp = timestamp;
        this.deleted = deleted;
        this.index = index;
        this.isMainPost = isMainPost;
        this.user = user;
        this.category = category;
        this.topic = topic;
        this.timeStampISO = timeStampISO;
    }

    // Constructor untuk konversi dari json ke java class
    public Post(JSONObject object){
        try {
            this.pid = object.getInt("pid");
            this.uid = object.getInt("uid");
            this.tid = object.getInt("tid");
            this.content = object.getString("content");
            this.timestamp = object.getLong("timestamp");
            this.deleted = object.getInt("deleted");
            this.index = object.getInt("index");
            this.isMainPost = object.getBoolean("isMainPost");

            User user = new User(object.getJSONObject("user"));
            this.user = user;

            Category category = new Category(object.getJSONObject("category"));
            this.category = category;

            Topic topic = new Topic(object.getJSONObject("topic"));
            this.topic = topic;

            this.timeStampISO = object.getString("timestampISO");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method untuk array of Json Object ke list of object
    // Category.fromJson(jsonArray);
    public static ArrayList<Post> fromJson(JSONArray jsonObjects) {
        ArrayList<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                posts.add(new Post(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isMainPost() {
        return isMainPost;
    }

    public void setMainPost(boolean mainPost) {
        isMainPost = mainPost;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
