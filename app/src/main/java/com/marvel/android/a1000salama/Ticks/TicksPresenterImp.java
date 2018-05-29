package com.marvel.android.a1000salama.Ticks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.Tick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmedpc on 25/5/2018.
 */

public class TicksPresenterImp implements TicksPresenter , ApiInterface {
    TicksView view;
    ArrayList<Tick> ticks = new ArrayList<>();
    @Override
    public void getTicks(int patientID) {
        view.showLoader();
        JSONObject authBody = new JSONObject();
        try {
            authBody.put("P1",patientID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        getOldTicks(authBody.toString(),ServicesConnection.CONTENT_TYPE);
    }

    @Override
    public void setView(TicksView view) {
        this.view = view;
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
        Call<String> QueryCall = ServicesConnection.GetService().getOldTicks(body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (response.isSuccessful()){
                    Log.e("dddddddddddddd",body);
                    try {

                        JSONArray ticksList = new JSONArray(body);

                        for (int i=0;i<ticksList.length();i++){
                            Tick tick = new Tick();
                            tick.setId(ticksList.getJSONObject(i).getInt("ID"));
                            tick.setSubject(ticksList.getJSONObject(i).getString("Subject"));
                            tick.setMessage(ticksList.getJSONObject(i).getString("Message"));
                            ticks.add(tick);
                        }

                        view.hideLoader();
                        if (ticks.size()>0)
                            view.setTicksList(ticks);
                        else
                            view.noOldTicks();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    view.hideLoader();
                    view.showError();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.hideLoader();
                view.showError();
            }
        });

        return null;
    }

    @Override
    public Call<String> uploadBookingPhotos(int P1, int P2, String P3, String P4, String P5) {
        return null;
    }


}
