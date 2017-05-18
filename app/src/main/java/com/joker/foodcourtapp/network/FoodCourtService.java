package com.joker.foodcourtapp.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.joker.foodcourtapp.utils.AppController;
import com.joker.foodcourtapp.utils.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by rick on 15/05/17.
 */
public class FoodCourtService {

    public static final String MAIN_API = "http://localhost:3000/";
    public static final String GET_ALL_TENANT = "getAllTenant";
    String message;

    public void getAllTenant(){
        Log.d("GET ALL TENANT", MAIN_API);

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                MAIN_API+GET_ALL_TENANT, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                    message = response.toString();
                    Log.d("GET ALL TENANT MESSAGE", message);

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR",error.toString());
                if (error instanceof NetworkError) {
                    message = "Network problem error";
                } else if (error instanceof ServerError) {
                    message = "Server problem error";
                } else if (error instanceof AuthFailureError) {
                    message = "AuthFailure problem error";
                } else if (error instanceof ParseError) {
                    message = "Parse error";
                } else if (error instanceof NoConnectionError) {
                    message = "No Connection Error";
                } else if (error instanceof TimeoutError) {
                    message = "TimeOut Error";
                } else {
                    message = "Unknown Error";
                }

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, "GET ALL TENANT");
    }
}
