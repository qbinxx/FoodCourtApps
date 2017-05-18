package com.joker.foodcourtapp.models;

/**
 * Created by rick on 29/04/17.
 */
public class Menu {

    private String name;
    private String imgUrl;

    public Menu() {
    }

    public Menu(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
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
