package com.marvel.android.a1000salama;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.marvel.android.a1000salama.DataBase.Contract;
import com.marvel.android.a1000salama.DataBase.alfSamalaSDBHelper;
import com.marvel.android.a1000salama.Login.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.Area;
import Model.Catoegry;
import Model.City;
import Model.Governrate;
import Model.ServiceSupplier;
import Model.SystemMessage;
import Model.SystemVersion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ahmed on 10/12/17.
 */

 public class Utils implements ApiInterface {
    Context context;
    Login loginView ;
    ArrayList<SystemVersion> systemVersions = new ArrayList<>();

    private static final String[] System_Message_Column = {
            Contract.alfsalamaEntry._ID,
            Contract.alfsalamaEntry.COLUMN_SystemMessages_ID,
            Contract.alfsalamaEntry.COLUMN_SystemMessages_message,

    };

    public Utils() {
    }

    public Utils(Context context) {
        this.context = context;
    }

    @Override
    public Call<String> login(String webserviceNumber, String body, String content_type) {


        return null;
    }

    @Override
    public Call<String> Registration(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getSystemMessages() {

        Call<String> QueryCall = ServicesConnection.GetService().getSystemMessages();
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String Body =   response.body();
                if (response.isSuccessful()) {
                    alfSamalaSDBHelper dbHelper = new alfSamalaSDBHelper(context);
                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        ContentValues values = new ContentValues();
                        JSONArray systemMessageArray = responCodeObj.getJSONArray("items");
                        for(int i = 0 ; i <systemMessageArray.length();i++ )
                        {
                           values.put(Contract.alfsalamaEntry.COLUMN_SystemMessages_ID,  systemMessageArray.getJSONObject(i).getString("id"));
                            values.put(Contract.alfsalamaEntry.COLUMN_SystemMessages_message,  systemMessageArray.getJSONObject(i).getString("message"));
                            context.getContentResolver().insert(Contract.alfsalamaEntry.CONTENT_URI, values);
                            dbHelper.insertSystemMessages(values);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());

            }
        });



        return null;
    }

    @Override
    public Call<String> getAllServiceProvider(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> BookService(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getBookingHistory(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getServices() {
        return null;
    }

    @Override
    public Call<String> getGovernates() {

        Call<String> QueryCall = ServicesConnection.GetService().getGovernates();
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String Body =   response.body();
                if (response.isSuccessful()) {
                    alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
                    Dbhelper.deleteGovernorate();
                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        JSONArray items = responCodeObj.getJSONArray("items");
                        for (int i = 0  ; i < items.length();i++)
                        {
                            Governrate gov = new Governrate();
                            gov.setID( items.getJSONObject(i).getInt("id"));
                           gov.setName(items.getJSONObject(i).getString("governorate_name"));

                            Dbhelper.InsertGovernate(gov);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());

            }
        });



        return null;
    }

    @Override
    public Call<String> getCities() {

        Call<String> QueryCall = ServicesConnection.GetService().getCities();
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String Body =   response.body();
                if (response.isSuccessful()) {
                    alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
                    Dbhelper.deleteCity();

                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        JSONArray items = responCodeObj.getJSONArray("items");
                        for (int i = 0  ; i < items.length();i++)
                        {
                            City city = new City();
                            city.setId( items.getJSONObject(i).getInt("id"));
                            city.setCityName(items.getJSONObject(i).getString("city_name"));
                            city.setGov_id(items.getJSONObject(i).getInt("governorate_id"));

                            Dbhelper.InsertCity(city);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());

            }
        });




        return null;
    }

    @Override
    public Call<String> getAreas() {


        Call<String> QueryCall = ServicesConnection.GetService().getAreas();
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String Body =   response.body();
                if (response.isSuccessful()) {
                    alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
                    Dbhelper.deleteArea();
                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        ContentValues values = new ContentValues();
                        JSONArray systemMessageArray = responCodeObj.getJSONArray("items");
                        for(int i = 0 ; i <systemMessageArray.length();i++ )
                        {

                            Area area = new Area();
                            area.setID( systemMessageArray.getJSONObject(i).getInt("id"));
                            area.setAreaName(systemMessageArray.getJSONObject(i).getString("area_name"));
                            area.setCity_ID(systemMessageArray.getJSONObject(i).getInt("city_id"));

                            Dbhelper.InsertArea(area);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());

            }
        });





        return null;
    }

    @Override
    public Call<String> getAllVersionList() {
        Call<String> getSystemVersion = ServicesConnection.GetService().getAllVersionList();
        getSystemVersion.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
                String Body =   response.body();
                if (response.isSuccessful()){

                    try {
                        JSONObject jsonObject = new JSONObject(Body);
                        JSONArray  array = jsonObject.getJSONArray("items");
                        for(int i=0;i<array.length();i++){
                            SystemVersion systemVersion = new SystemVersion();
                            systemVersion.setId(array.getJSONObject(i).getInt("id"));
                            systemVersion.setCode(array.getJSONObject(i).getString("code"));
                            systemVersion.setValue(array.getJSONObject(i).getDouble("value"));
                            systemVersion.setDescribtion(array.getJSONObject(i).getString("describtion"));
                            systemVersions.add(systemVersion);
                        }

                        for (int i=0;i<systemVersions.size();i++){
                            if(systemVersions.get(i).getCode().equalsIgnoreCase("governorate")){
                                if (SharedPrefManager.getInstance(context).getGovernorateVersion() !=
                                        (float) systemVersions.get(i).getValue() ){
                                    getGovernates();
                                    SharedPrefManager.getInstance(context).setGovernorateVersion((float) systemVersions.get(i).getValue());

                                }


                            }

                            if(systemVersions.get(i).getCode().equalsIgnoreCase("city")){
                                if (SharedPrefManager.getInstance(context).getCityVersion() !=
                                        (float) systemVersions.get(i).getValue() ){
                                    getCities();
                                    SharedPrefManager.getInstance(context).setCityVersion((float) systemVersions.get(i).getValue());

                                }


                            }

                            if(systemVersions.get(i).getCode().equalsIgnoreCase("area")){
                                if (SharedPrefManager.getInstance(context).getAreaVersion() !=
                                        (float) systemVersions.get(i).getValue() ){
                                    getAreas();
                                    SharedPrefManager.getInstance(context).setAreaVersion((float) systemVersions.get(i).getValue());

                                }


                            }


                            if(systemVersions.get(i).getCode().equalsIgnoreCase("category")){
                                if (SharedPrefManager.getInstance(context).getCategoryVersion() !=
                                        (float) systemVersions.get(i).getValue() ){
                                    String body = "\n" +
                                            "{\n" +
                                            "\t\"P1\":\"tab02j\"\n" +
                                            "}";

                                    getCat(body , ServicesConnection.CONTENT_TYPE);
                                    SharedPrefManager.getInstance(context).setCategoryVersion((float) systemVersions.get(i).getValue());

                                }



                            }

                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        return null;
    }

    @Override
    public Call<String> getCat( String body, String content_type) {
        Call<String> QueryCall = ServicesConnection.GetService().getCat(body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String Body =   response.body();
                if (response.isSuccessful()) {

                    alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
                    Dbhelper.deleteCategory();

                    try {
                       JSONArray responCodeObj = new JSONArray(Body);
                        for(int i = 0 ; i <responCodeObj.length();i++ )
                        {

                            Catoegry catoegry = new Catoegry();
                            catoegry.setCatID( responCodeObj.getJSONObject(i).getString("LOOKUP_ID"));
                            catoegry.setCatName(responCodeObj.getJSONObject(i).getString("LOOKUP_DESC"));

                            Dbhelper.InsertCAT(catoegry);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());

            }
        });

            return  null;

    }

    @Override
    public Call<String> rateService(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetComments(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetBranchServices(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetBrnchrates(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetAboutUsServiceProvidor(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> sendToUs(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getOldTicks(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> uploadBookingPhotos(int P1, int P2, String P3, String P4, String P5) {
        return null;
    }

    @Override
    public Call<String> editUserData(String body, String content_type) {
        return null;
    }

    @Override
    public Call<ServiceSupplier> getServiceSuppliers() {
        return null;
    }


    public static void slide_down(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    public static void slide_up(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    public  List<SystemMessage> getSystemMessage(String id){


        List<SystemMessage> results = new ArrayList<>();


        try {
            Cursor cursor = context.getContentResolver().query(
                    Contract.alfsalamaEntry.CONTENT_URI,
                    System_Message_Column,
                    Contract.alfsalamaEntry.COLUMN_SystemMessages_ID + "='" + id + "'",
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    SystemMessage systemMessage = new SystemMessage(cursor);
                    results.add(systemMessage);
                } while (cursor.moveToNext());
                cursor.close();
            }

        }
        catch (Exception e)
        {

        }
       return results;
    }


    public  static int getUserID(Context context){

        int id = -1;
        SharedPreferences prefs = context.getSharedPreferences("RemeberMe", MODE_PRIVATE);
        int restoredText = prefs.getInt("id", -1);
        if (restoredText != -1) {
             id = prefs.getInt("id", -1); //0 is the default value.
        }
        return id;
    }

    public  static void RemoveUserID(Context context){

        SharedPreferences.Editor editor = context.getSharedPreferences("RemeberMe", MODE_PRIVATE).edit();
        editor.putInt("id",-1);
        editor.apply();;
    }

    public static final boolean isInternetOn(Context  context) {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

          //  Toast.makeText(context, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

         //   Toast.makeText(context, "من  فضلك تأكد من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

}
