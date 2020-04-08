package com.example.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private static Preference instance;
    private SharedPreferences preferences;

    public static Preference getInstance (Context context){
        if (instance == null){
            instance = new Preference(context);
        }

        return instance;
    }

    private Preference(Context context){
        preferences = context.getSharedPreferences("NewsApp", Context.MODE_PRIVATE);
    }

    public void put(String key, String value){
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        } catch (Exception e){

        }
    }

    public String getString(String key){
        return preferences.getString(key, "");
    }

    public void put(String key, int value){
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            editor.apply();
        } catch (Exception e){

        }
    }

    public int getInt(String key){
        return preferences.getInt(key, 0);
    }

    public void put(String key, long value){
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(key, value);
            editor.apply();
        } catch (Exception e){

        }
    }

    public long getLong(String key){
        return preferences.getLong(key, 0);
    }

    public void put(String key, boolean value){
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        } catch (Exception e){

        }
    }

    public boolean getBoolean(String key){
        return preferences.getBoolean(key, false);
    }

    public void remove(String key){
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            editor.apply();
        } catch (Exception e) {

        }
    }

}
