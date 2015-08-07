package com.example.tony.stalkerapp;

import android.content.Context;
import android.content.SharedPreferences;


public class InstagramSession {
    protected SharedPreferences sharedPref;
    protected SharedPreferences.Editor editor;
    protected static final String SHARED = "Instagram_Preferences";
    protected static final String API_USERNAME = "username";
    protected static final String API_ID = "id";
    protected static final String API_NAME = "name";
    protected static final String API_ACCESS_TOKEN = "access_token";
    public InstagramSession(Context context) {
        sharedPref = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void storeAccessToken(String accessToken, String id, String username, String name) {
        editor.putString(API_ID, id);
        editor.putString(API_NAME, name);
        editor.putString(API_ACCESS_TOKEN, accessToken);
        editor.putString(API_USERNAME, username);
        editor.commit();
    }
    public void storeAccessToken(String accessToken) {
        editor.putString(API_ACCESS_TOKEN, accessToken);
        editor.commit();
    }
    /**
     * Reset access token and user name
     */
    public void resetAccessToken() {
        editor.putString(API_ID, null);
        editor.putString(API_NAME, null);
        editor.putString(API_ACCESS_TOKEN, null);
        editor.putString(API_USERNAME, null);
        editor.commit();
    }
    /**
     * Get user name
     *
     * @return User name
     */
    public String getUsername() {
        return sharedPref.getString(API_USERNAME, null);
    }


    public String getId() {
        return sharedPref.getString(API_ID, null);
    }

    public String getName() {
        return sharedPref.getString(API_NAME, null);
    }

    public String getAccessToken() {
        return sharedPref.getString(API_ACCESS_TOKEN, null);
    }
}

