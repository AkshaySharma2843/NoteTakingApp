package com.call.notetakingapp;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferencesManager {

    private static String sharedPRefName = "note_taking_app";
    private static SharedPreferences sharedPreferences;
     private SharedPreferences.Editor sharedPrefEditor;



    public static SharedPreferences createSharedPreferences(Context context){
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(sharedPRefName, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }



}
