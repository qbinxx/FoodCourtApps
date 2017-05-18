package com.joker.foodcourtapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rick on 07/08/16.
 */
public class Category {

    private int cid;
    private String name;
    private String icon;
    private String bgColor;
    private String color;
    private String slug;
    private String imgUrl;
    private int parentCid;
    private boolean disabled;
    private ArrayList<Menu> listMenu = new ArrayList<>();

    public Category(){

    }

    public Category(String name,String url){
        this.name = name;
        this.slug = url;
    }

    public Category(int cid, String name, String icon, String bgColor, String color, String slugr, int parentCid, boolean disabled) {
        this.cid = cid;
        this.name = name;
        this.icon = icon;
        this.bgColor = bgColor;
        this.color = color;
        this.parentCid = parentCid;
        this.disabled = disabled;
    }

    // Constructor untuk konversi dari json ke java class
    public Category(JSONObject object){
        try {
            this.cid = object.getInt("cid");
            this.name = object.getString("name");
            this.icon = object.getString("icon");
            this.bgColor = object.getString("bgColor");
            this.color = object.getString("color");
            this.slug = object.getString("slug");
            this.parentCid = object.getInt("parentCid");
            this.disabled = object.getBoolean("disabled");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method untuk array of Json Object ke list of object
    // Category.fromJson(jsonArray);
    public static ArrayList<Category> fromJson(JSONArray jsonObjects) {
        ArrayList<Category> categories = new ArrayList<Category>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                categories.add(new Category(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getParentCid() {
        return parentCid;
    }

    public void setParentCid(int parentCid) {
        this.parentCid = parentCid;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ArrayList<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }
}
