package com.joker.foodcourtapp.models;

/**
 * Created by rick on 29/04/17.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Menu2 implements Parcelable{
    private String name;
    private float price;

    public Menu2(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public Menu2(String name) {
        this.name = name;
    }

    public Menu2(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Menu2> CREATOR = new Creator<Menu2>() {
        @Override
        public Menu2 createFromParcel(Parcel in) {
            return new Menu2(in);
        }

        @Override
        public Menu2[] newArray(int size) {
            return new Menu2[size];
        }
    };
}
