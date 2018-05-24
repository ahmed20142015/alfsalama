package com.marvel.android.a1000salama.Rating;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.Rate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 10/05/18.
 */

public class RatingPresneterImpl implements RatingPresneter , ApiInterface {



    private RatingViweInterFace rateViwe;
    private ArrayList<Rate> RateList;


    @Override
    public void setView(RatingFragment RatingFragment) {

        this.rateViwe = RatingFragment;
        RateList = new ArrayList<>();
    }

    public RatingViweInterFace getRateViwe() {
        return rateViwe;
    }

    public void setRateViwe(RatingViweInterFace rateViwe) {
        this.rateViwe = rateViwe;
    }

    public ArrayList<Rate> getRateList() {
        return RateList;
    }

    public void setRateList(ArrayList<Rate> rateList) {
        RateList = rateList;
    }

    @Override
    public void getRates(int SPID) {

        String BookServiceBody= "{";

        BookServiceBody +="\"P1\":"+SPID+" ";


        BookServiceBody +="}";
        // commentsVew.showLoader();
        GetBrnchrates(BookServiceBody, ServicesConnection.CONTENT_TYPE);
    }


    @Override
    public Call<String> GetBrnchrates(String body, String content_type) {

        Call<String> QueryCall = ServicesConnection.GetService().GetBrnchrates(body,content_type);
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
                            Rate rate = new Rate();
                            rate.setPatintNamel(responCodeObj.getJSONObject(i).getString("PATIENT_Name"));
                            rate.setRating( responCodeObj.getJSONObject(i).getLong("RATING"));
                            RateList.add(rate);
                        }


                        rateViwe.setRatesList(RateList);
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
    public Call<String> GetAboutUsServiceProvidor(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> sendToUs(String body, String content_type) {
        return null;
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
    public Call<String> GetBranchServices(String body, String content_type) {
        return null;
    }



}


