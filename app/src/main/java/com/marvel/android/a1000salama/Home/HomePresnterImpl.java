package com.marvel.android.a1000salama.Home;

import android.content.Context;

import com.marvel.android.a1000salama.DataBase.alfSamalaSDBHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.Area;
import Model.Catoegry;
import Model.City;
import Model.Governrate;
import Model.ServiceProidveritem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 26/01/18.
 */

public class HomePresnterImpl  implements HomePersenter  , ApiInterface {

    HomeViwe viwe ;
    public ArrayList<ServiceProidveritem> ServiceProivderList = new ArrayList<>();


    public ArrayList<ServiceProidveritem> getServiceProivderList() {
        return ServiceProivderList;
    }

    public void setServiceProivderList(ArrayList<ServiceProidveritem> serviceProivderList) {
        ServiceProivderList = serviceProivderList;
    }

    @Override
    public void setView(HomeViwe HomeViwe) {
        this.viwe = HomeViwe;

    }

    public int GetAllServicesProivders(String BranchName, String Gover_id, String city_id, String earea_id, ArrayList<String> series_ids
    , String catID) {

        String GetAllSpBody= "{";
            if(BranchName.equals(""))
            {
                GetAllSpBody +="\"P1\":null ,";

            }
            else
                GetAllSpBody +="\"P1\":\""+BranchName+"\",";
            if(Gover_id.equals(""))
            {
                GetAllSpBody +="\"P2\":null ,";


            }
            else
                GetAllSpBody +="\"P2\":\""+Gover_id+"\" ,";

            if(city_id.equals(""))
            {

                GetAllSpBody +="\"P3\":null ,";

            }
            else
                GetAllSpBody +="\"P3\":\""+city_id+"\" ,";
            if(earea_id.equals(""))
            {
                GetAllSpBody +="\"P4\":null ,";


            }
            else
                GetAllSpBody +="\"P4\":\""+earea_id+" \",";
            if(series_ids.size()==0)
            {
                GetAllSpBody +="\"P5\":null ,";

            }
            else
                GetAllSpBody +="\"P5\":null ,";



        if(catID.equals(""))
        {
            GetAllSpBody +="\"P6\":null";

        }
        else
            GetAllSpBody +="\"P6\":"+catID+"";

        GetAllSpBody +="}";
        viwe.showLoader();
        getAllServiceProvider("WS4",GetAllSpBody.toString(), ServicesConnection.CONTENT_TYPE);

        return 0;
    }

    @Override
    public int GetGovrnates(Context context) {
        viwe.showLoader();
        alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
        ArrayList<Governrate > govs =Dbhelper.getAllGovernrate();
        viwe.setGovList(govs);
        return 0;
    }

    @Override
    public int GetCities(Context context) {
        viwe.showLoader();
        alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
        ArrayList<City> cities =Dbhelper.getAllCities();
        viwe.setCityList(cities);
        return 0;

    }

    @Override
    public int GetAreas(Context context) {
        viwe.showLoader();
        alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
        ArrayList<Area> Areas =Dbhelper.getAllAreaies();
        viwe.setAreaList(Areas);
        return 0;
    }

    @Override
    public int GetCats(Context context) {
        viwe.showLoader();
        alfSamalaSDBHelper Dbhelper = new alfSamalaSDBHelper(context);
        ArrayList<Catoegry> cats =Dbhelper.getAllCats();
        viwe.setCatList(cats);

        return 0;
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
        return null;
    }
    @Override
    public Call<String> getAllServiceProvider(String webserviceNumber, String body, String content_type) {
        String Return = "-1";

        Call<String> QueryCall = ServicesConnection.GetService().getAllServiceProvider("WS4",body,content_type);

        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {

                    JSONArray Service_ProvidorList = null;
                    try {
                        ServiceProivderList = new ArrayList<>();
                         Service_ProvidorList = new JSONArray(Body);
                      //  JSONArray Service_ProvidorList =responCodeObj.getJSONArray("Service Providor");
                        for(int i= 0 ; i < Service_ProvidorList.length() ; i++)
                        {

                            /*
                            "X_CORDINATE":29.975964
,"Y_CORDINATE":31.130769
,"LOGO_URL":"http:\/\/nt-me.com\/wp-content\/uploads\/2016\/12\/AlfaLab.jpg"
,"OVER_ALL_RATING":3.3
                             */
                            ServiceProidveritem SP = new ServiceProidveritem();
                            SP.setSP_ID(Service_ProvidorList.getJSONObject(i).getInt("SC_ID"));
                            SP.setBRANCH_ID(Service_ProvidorList.getJSONObject(i).getInt("SC_BRANCH_ID"));
                            SP.setSP_Name(Service_ProvidorList.getJSONObject(i).getString("SP_NAME"));
                            SP.setImageURl(Service_ProvidorList.getJSONObject(i).getString("LOGO_URL").replace("\\",""));

                           try {

                               SP.setCordX(Service_ProvidorList.getJSONObject(i).getString("X_CORDINATE"));
                               SP.setCordY(Service_ProvidorList.getJSONObject(i).getString("Y_CORDINATE"));
                           }
                           catch (Exception e)
                           {

                           }

                            SP.setOverallRating(Service_ProvidorList.getJSONObject(i).getLong("OVER_ALL_RATING"));


                            try {
                                SP.setGOVERNORATE_ID(Service_ProvidorList.getJSONObject(i).getInt("SC_ID"));

                            }
                            catch (Exception e)
                            {

                            }
                            try {
                                SP.setGOVERNORATE_ID(Service_ProvidorList.getJSONObject(i).getInt("GOVERNORATE_ID"));

                            }
                            catch (Exception e)
                            {

                            }
                            try {
                                SP.setAREA_ID(Service_ProvidorList.getJSONObject(i).getInt("AREA_ID"));

                            }
                            catch (Exception e)
                            {

                            }

                            try {
                                SP.setStreetID(Service_ProvidorList.getJSONObject(i).getInt("ST"));

                            }
                            catch (Exception e)
                            {

                            }
                            ServiceProivderList.add(SP);


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    viwe.hideLoader();
                    viwe.setSPList(ServiceProivderList);

                }

                viwe.hideLoader();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());
                viwe.hideLoader();
            }
        });

        //ServicesConnection.GetService().getAllServiceProvider("WS4",body.toString(), ServicesConnection.CONTENT_TYPE);


    return  null;
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
        return null;
    }

    @Override
    public Call<String> getCities() {
        return null;
    }

    @Override
    public Call<String> getAreas() {
        return null;
    }

    @Override
    public Call<String> getCat(String body, String content_type) {



        return null;
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
}
