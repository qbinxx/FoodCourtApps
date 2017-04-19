package com.joker.foodcourtapp.network;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.joker.foodcourtapp.utils.AppController;
import com.joker.foodcourtapp.utils.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SquareService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_LOGIN = "com.joker.qeponsquareppni.action.LOGIN";
    private static final String ACTION_REGISTRATION = "com.joker.qeponsquareppni.action.REGISTRATION";
    private static final String ACTION_GENERAL_GET = "com.joker.qeponsquareppni.action.GENGET";
    private static final String ACTION_MAIN = "com.joker.qeponsquareppni.action.MAIN";

    // TODO: Rename parameters
    private static final String URL = "url";
    private static final String EVENT = "event";
    private static final String TAG = "tag";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";

    public SquareService() {
        super("SquareService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void actionLogin(Context context, String username, String password, String csrf_token) {
        Intent intent = new Intent(context, SquareService.class);
        intent.setAction(ACTION_LOGIN);
        intent.putExtra(USERNAME, username);
        intent.putExtra(PASSWORD, password);
        intent.putExtra(Constant.HEADER_XCSRF_KEY, csrf_token);
        context.startService(intent);
    }

    public static void actionRegistration(Context context, String username, String password,String email, String csrf_token) {
        Intent intent = new Intent(context, SquareService.class);
        intent.setAction(ACTION_REGISTRATION);
        intent.putExtra(USERNAME, username);
        intent.putExtra(PASSWORD, password);
        intent.putExtra(EMAIL, email);
        intent.putExtra(Constant.HEADER_XCSRF_KEY, csrf_token);
        context.startService(intent);
    }

    public static void actionGeneralGet(Context context, String url,String event, String csrf_token, String tag) {
        Intent intent = new Intent(context, SquareService.class);
        intent.setAction(ACTION_GENERAL_GET);
        intent.putExtra(URL, url);
        intent.putExtra(EVENT, event);
        intent.putExtra(Constant.HEADER_XCSRF_KEY, csrf_token);
        intent.putExtra(TAG, tag);
        context.startService(intent);
    }

    public static void actionLoadMainPageData(Context context) {
        Intent intent = new Intent(context, SquareService.class);
        intent.setAction(ACTION_MAIN);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LOGIN.equals(action)) {
                final String username = intent.getStringExtra(USERNAME);
                final String password = intent.getStringExtra(PASSWORD);
                final String csrf_token = intent.getStringExtra(Constant.HEADER_XCSRF_KEY);
                login(username, password, csrf_token);
            } else if(ACTION_REGISTRATION.equals(action)){
                final String username = intent.getStringExtra(USERNAME);
                final String password = intent.getStringExtra(PASSWORD);
                final String email = intent.getStringExtra(EMAIL);
                final String csrf_token = intent.getStringExtra(Constant.HEADER_XCSRF_KEY);
                registration(username,password,email,csrf_token);
            } else if(ACTION_GENERAL_GET.equals(action)){
                final String url = intent.getStringExtra(URL);
                final String event = intent.getStringExtra(EVENT);
                final String csrf_token = intent.getStringExtra(Constant.HEADER_XCSRF_KEY);
                final String tag = intent.getStringExtra(TAG);
                generalGet(url,event,csrf_token,tag);
            } else if(ACTION_MAIN.equals(action)){
                getMainPage();
            }

        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void getMainPage(){
        Log.d("GET MAIN", Constant.MAIN_API);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Constant.MAIN_API, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        sendMessage(response.toString(), "my-event-main");
                    }
                }, responseErrorListener("my-event-main"));

        AppController.getInstance().addToRequestQueue(jsonObjReq, "GET CONFIG");
    }

    private void login(final String username, final String password,final String csrf_token) {
        // TODO: Handle action Login
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        JSONObject param = new JSONObject(params);
        Log.d("LOGIN", param.toString());
        Log.d("LOGIN", csrf_token);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_LOGIN + "?username=" + username + "&password=" + password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("LOGIN", response);
                sendMessage(response, "my-event-login");
            }
        }, responseErrorListener("my-event-login")) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("X-CSRF-TOKEN", csrf_token);
                headers.put("charset", "utf-8");

                Log.w("Header", headers.toString());
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(stringRequest, "LOGIN");
    }

    private void registration(final String username, String password, String email, final String csrf_token) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        params.put("_uid", "1");

        JSONObject param = new JSONObject(params);
        Log.d("REGISTRATION", param.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constant.USERS_API_V1, param,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("REGISTRATION", response.toString());
                        sendMessage(response.toString(), "my-event-registration");
                    }
                }, responseErrorListener("my-event-registration"))
        {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("X-CSRF-TOKEN", csrf_token);
                headers.put("Authorization", Constant.HVALUE_AUTHORIZATION);

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjReq, "SIGN_UP_USER");
    }

    private void generalGet(String url, final String event, final String csrf_token, String TAG) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        sendMessage(response.toString(), event);
                    }
                }, responseErrorListener(event)) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("X-CSRF-TOKEN", csrf_token);
                headers.put("Authorization", Constant.HVALUE_AUTHORIZATION);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq, TAG);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendMessage(String message, String event) {

        Intent intent = new Intent(event);
        intent.putExtra("message", message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }

    private Response.ErrorListener responseErrorListener(final String event) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR",error.toString());
                if (error instanceof NetworkError) {
                    sendMessage("Network problem error", event);
                } else if (error instanceof ServerError) {
                    sendMessage("Server problem error", event);
                } else if (error instanceof AuthFailureError) {
                    sendMessage("AuthFailure problem error", event);
                } else if (error instanceof ParseError) {
                    sendMessage("Parse error", event);
                } else if (error instanceof NoConnectionError) {
                    sendMessage("NoConnection error", event);
                } else if (error instanceof TimeoutError) {
                    sendMessage("TimeOut error", event);
                } else {
                    sendMessage("Unknown error", event);
                }

            }
        };
    }
}
