package com.marvel.android.a1000salama;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Eslam on 3/11/2018.
 */

public class SharedPrefManager {
    private static final String SHARES_PREF_NAME = "sharedPref";
    private static final String KEY_Governorate = "keygovernorate";
    private static final String KEY_City = "keycity";
    private static final String KEY_Area = "keyarea";
    private static final String KEY_Category = "keycategory";

    private static SharedPrefManager sharedPrefManager;
    private static Context mContext;
    SharedPreferences sharedPref ;
    private SharedPrefManager (Context context){
        mContext=context;
        sharedPref = mContext.getSharedPreferences(SHARES_PREF_NAME,Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if(sharedPrefManager == null){
            sharedPrefManager = new SharedPrefManager(context);
        }

        return sharedPrefManager;
    }


    public void setGovernorateVersion(float value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(KEY_Governorate,value);
        editor.apply();
    }

    public float getGovernorateVersion(){
        return sharedPref.getFloat(KEY_Governorate,-1f);
    }

    public void setCityVersion(float value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(KEY_City,value);
        editor.apply();
    }

    public float getCityVersion(){
        return sharedPref.getFloat(KEY_City,-1f);
    }

    public void setAreaVersion(float value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(KEY_Area,value);
        editor.apply();
    }

    public float getAreaVersion(){
        return sharedPref.getFloat(KEY_Area,-1f);
    }

    public void setCategoryVersion(float value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(KEY_Category,value);
        editor.apply();
    }

    public float getCategoryVersion(){
        return sharedPref.getFloat(KEY_Category,-1f);
    }


}
