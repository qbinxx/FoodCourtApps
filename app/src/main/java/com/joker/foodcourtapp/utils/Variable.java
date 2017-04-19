package com.joker.foodcourtapp.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by rick on 02/09/16.
 */
public class Variable extends Application {

    private static String PREF_NAME = "myPref";

    public static void setMainPageData(Context context, String value){
        Util.saveString(context,PREF_NAME,"main_page_data",value);
    }

    public static String getMainPageData(Context context){
        return Util.loadString(context,PREF_NAME,"main_page_data");
    }

    public static void setCsrfToken(Context context, String value){
        Util.saveString(context,PREF_NAME,"csrf_token",value);
    }

    public static String getCsrfToken(Context context){
        return Util.loadString(context,PREF_NAME,"csrf_token");
    }

    public static void setUserDataLogged(Context context, String value){
        Util.saveString(context,PREF_NAME,"user_data_logged",value);
    }

    public static String getUserDataLogged(Context context){
        return Util.loadString(context,PREF_NAME,"user_data_logged");
    }

    public static void setFirstOpen(Context context,Boolean value){
        Util.saveBoolean(context,PREF_NAME,"firstOpen",value);
    }

    public static Boolean getFirstOpen(Context context){
        return Util.loadBoolean(context,PREF_NAME,"firstOpen");
    }

    public static void setLoginStatus(Context context,Boolean value){
        Util.saveBoolean(context,PREF_NAME,"logined",value);
    }

    public static Boolean getLoginStatus(Context context){
        return Util.loadBoolean(context,PREF_NAME,"logined");
    }

    public static void setMiniCardConf(Context context,Boolean value){
        Util.saveBoolean(context,PREF_NAME,"mini_card",value);
    }

    public static Boolean getMiniCardConf(Context context){
        return Util.loadBoolean(context,PREF_NAME,"mini_card");
    }


}
