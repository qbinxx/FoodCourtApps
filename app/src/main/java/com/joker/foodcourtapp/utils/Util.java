package com.joker.foodcourtapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rick on 05/09/16.
 */
public class Util {

    public static void saveString(Context c, String prefName, String key, String value) {
        SharedPreferences settings = c.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String loadString(Context c, String prefName, String key){
        SharedPreferences settings = c.getSharedPreferences(prefName, 0);
        return settings.getString(key, "");
    }

    public static void saveBoolean(Context c, String prefName,String key,Boolean value){
        SharedPreferences settings = c.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean loadBoolean(Context c,String prefName, String key){
        SharedPreferences settings = c.getSharedPreferences(prefName, 0);
        return settings.getBoolean(key,false);
    }
}
