package com.zorina.lk.zorina.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AdminSessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private static final String PREF_NAME = "AdminSession";
    private static final String KEY_Admin = "admin";

    public AdminSessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }


    public void saveAdmin(AdminAuth adminAuth) {
        String json = gson.toJson(adminAuth);
        editor.putString(KEY_Admin, json);
        editor.apply();
    }

    public AdminAuth getAdmin() {
        String json = sharedPreferences.getString(KEY_Admin, null);
        return gson.fromJson(json, AdminAuth.class);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.contains(KEY_Admin);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
