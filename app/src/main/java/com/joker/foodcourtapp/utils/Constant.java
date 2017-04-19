package com.joker.foodcourtapp.utils;

/**
 * Created by rick on 25/08/16.
 */
public class Constant {

    public static final String PREFS_NAME = "MyPrefsFile";

    // Categories Constant
    public static final String[] TITLE_TABS = {"TENANT", "FOOD", "DRINK"};
    public static final int[] TABS_CID = {1, 9, 10};

    // API
    public static final String ROOT_URL = "http://128.199.73.20:4567";
    public static final String USERS_API = ROOT_URL +"/api/users";
    public static final String USERS_API_V1 = ROOT_URL +"/api/v1/users";
    public static final String USER_API = ROOT_URL +"/api/user";
    public static final String MAIN_API = ROOT_URL +"/api";
    public static final String CONFIG_API = ROOT_URL +"/api/config";
    public static final String URL_LOGIN = ROOT_URL +"/login";
    public static final String CATEGORY_API = ROOT_URL +"/api/category";

    // Header
    public static final String HEADER_XCSRF_KEY = "X-CSRF-TOKEN";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CHARSET = "charset";
        //Value
        public static final String HVALUE_XCSRF_TOKEN = "kiXjEN5H-yTzxBzJ_4AMrOQeSopYw7HYb9Yo";
        public static final String HVALUE_CONTENT_TYPE = "application/json";
        public static final String HVALUE_AUTHORIZATION = "Bearer 719b5d0a-3d12-4f0a-b9fc-419bbb613d94";
        public static final String HVALUE_CHARSET = "utf-8";

    //FOOD COURT AREA
    public static final int PARENT_CATEGORY_SIZE = 3;


}
