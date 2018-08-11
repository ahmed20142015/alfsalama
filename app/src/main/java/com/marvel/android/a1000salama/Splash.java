package com.marvel.android.a1000salama;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.marvel.android.a1000salama.DataBase.alfSamalaSDBHelper;
import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.Login.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import APIClient.ServicesConnection;
import Model.Area;
import Model.Catoegry;
import Model.City;
import Model.Governrate;
import Model.SystemMessage;

public class Splash extends Activity{

    List<SystemMessage> SysMessageList = new ArrayList<>();
    Utils utils = new Utils();
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
      //  getSupportActionBar().hide();

        Locale locale = new Locale("ar");
        Locale.setDefault(locale);

       String en = Locale.getDefault().getDisplayLanguage();


        Resources resources = this.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }


        StartAnimations();
//        Intent i;
//                   i = new Intent(Splash.this, Login.class);
//                    startActivity(i);
//         finish();


    }


    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this,  android.R.anim.fade_in);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {

                   new  GetSystemMessages().execute();
                    //   Thread.sleep(1000);

                } catch (Exception e) {

                }
            }
        }, 1000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }




    public class GetSystemMessages extends AsyncTask<Object, Object, List<SystemMessage>> {




        @Override
        protected List<SystemMessage> doInBackground(Object... params) {
            utils = new Utils(Splash.this);
            utils.getSystemMessages();

            utils.getAllVersionList();

//            alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(Splash.this);
//            ArrayList<Governrate> govs =Dbhelper.getAllGovernrate();
//            if(govs.size()>0)
//            {
//
//            }
//            else
//            utils.getGovernates();
//
//
//            ArrayList<City> cityArrayList =Dbhelper.getAllCities();
//            if(cityArrayList.size()>0)
//            {
//
//            }
//            else
//                utils.getCities();
//
//
//            ArrayList<Area> areaArrayList =Dbhelper.getAllAreaies();
//            if(areaArrayList.size()>0)
//            {
//
//            }
//            else
//                utils.getAreas();
//
//
//            ArrayList<Catoegry> CatoegryArrayList =Dbhelper.getAllCats();
//            if(CatoegryArrayList.size()>0)
//            {
//
//            }
//            else {
//                String Body = "\n" +
//                        "{\n" +
//                        "\t\"P1\":\"tab02j\"\n" +
//                        "}";
//
//                utils.getCat(Body , ServicesConnection.CONTENT_TYPE);
//            }

            return SysMessageList;

        }

        @Override
        protected void onPostExecute(List<SystemMessage> SysMessageList) {
            Intent i;
            if(Utils.getUserID(Splash.this)==-1)
            {
                i = new Intent(Splash.this, Login.class);
            }
            else
            {
                i = new Intent(Splash.this, Home.class);
            }

            startActivity(i);

            finish();
        }
    }








}
