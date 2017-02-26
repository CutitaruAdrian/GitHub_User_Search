package com.example.cutit_000.github_user_search.Network;


import android.app.Application;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cutit_000.github_user_search.Models.Repository;
import com.example.cutit_000.github_user_search.interfaces.IRepositoryReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by cutit_000 on 14.02.2017.
 */

public class ApiClient extends Application {
    private final static String baseUrl = "https://api.github.com/users/";
    private static final String TAG = ApiClient.class.getSimpleName();

    public static boolean checkUser(String username) {

        boolean success = false;
        HttpsURLConnection connection;


        try {
            URL url = new URL(baseUrl + username);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                success = TRUE;
            } else {
                success = FALSE;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }


    public void getUser(String username, Response.Listener<JSONObject> listener) {
        String url = (baseUrl + username);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, listener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });
        ApplicationController.getInstance().addToRequestQueue(req);
    }


    public void getRepositories(final String username, final IRepositoryReceiver receiver) {
        String url = (baseUrl + username + "/repos");
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                try {
                    Repository[] repositories = new Repository[response.length()];
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject r = (JSONObject) response.get(i);
                        Integer id = r.getInt("id");
                        String name = r.getString("name");
                        String description = r.getString("description");
                        repositories[i] = new Repository(id, name, username, description);
                    }
                    receiver.onRepositoryReceived(repositories);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(req);
    }
}
