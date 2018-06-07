package com.marvel.android.a1000salama.app_font;

import android.app.Application;

/**
 * Created by ahmedpc on 7/6/2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/GE_SS_Text_Light.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}