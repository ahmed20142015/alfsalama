package com.marvel.android.a1000salama.ServicsDetails;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 10/05/18.
 */

public class ServiceDetlaisPresnterImpl implements ServiceDetalisPresneter , ApiInterface {


   private ServiceDetialinterface services;
   private  ArrayList<Services>servicesList;



    @Override
    public void setView(BServiceDetailsFragment services) {
        this.services = services;
        servicesList=new ArrayList<>();

    }


    @Override
    public void getServices(int SPID) {


        String BookServiceBody= "{";

        BookServiceBody +="\"P1\":"+SPID+" ";


        BookServiceBody +="}";
        // commentsVew.showLoader();
        GetBranchServices(BookServiceBody, ServicesConnection.CONTENT_TYPE);
    }

    @Override
    public Call<String> GetBranchServices(String body, String content_type) {
        Call<String> QueryCall = ServicesConnection.GetService().GetBranchServices(body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {
                    //P1OUT


                    try {
                        JSONArray responCodeObj = new JSONArray(Body);

                        for(int i = 0 ; i  < responCodeObj.length();i++)
                        {
                            Services services = new Services();
                           services.setName(responCodeObj.getJSONObject(i).getString("SERVICE_NAME"));
                            services.setPRICE_BEFORE_DISCOUNT( responCodeObj.getJSONObject(i).getInt("PRICE BEFORE DISCOUNT"));
                            services.setPRICE_After_DISCOUNT(responCodeObj.getJSONObject(i).getInt("PRICE AFTER DISCOUNT"));
                            servicesList.add(services);
                        }


                    services.setServicessList(servicesList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    //   BookingFragViwe.hideLoader();
                    //viwe.setSPList(ServiceProivderList);

                }
                else {
                    //    BookingFragViwe.hideLoader();
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());
                //    BookingFragViwe.hideLoader();
            }
        });
        return  null;
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
    public Call<String> GetBrnchrates(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetServiceProvidor(String body, String content_type) {
        return null;
    }


}
