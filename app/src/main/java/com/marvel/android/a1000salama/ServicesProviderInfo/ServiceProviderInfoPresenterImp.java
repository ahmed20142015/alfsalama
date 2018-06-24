package com.marvel.android.a1000salama.ServicesProviderInfo;

import com.marvel.android.a1000salama.Login.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.AboutServiceProvidor;
import Model.ServiceSupplier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmedpc on 22/5/2018.
 */

public class ServiceProviderInfoPresenterImp implements ServiceProviderInfoPresenter, ApiInterface {
    ServiceProviderInfoView view;
    ArrayList<AboutServiceProvidor> aboutProvidorList = new ArrayList();
    @Override
    public void setView(ServiceProviderInfoView view) {
        this.view = view;
    }

    @Override
    public int RequestAboutServiceProvidor(int branchId) {
        view.showLoader();
        JSONObject authBody = new JSONObject();
        try {
            authBody.put("P1",branchId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GetAboutUsServiceProvidor(authBody.toString(),ServicesConnection.CONTENT_TYPE);
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
        Call<String> QueryCall = ServicesConnection.GetService().GetAboutUsServiceProvidor(body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (response.isSuccessful()){
                    try {
                        JSONArray aboutProvidor = new JSONArray(body);
                        for(int i=0;i<aboutProvidor.length();i++){
                            AboutServiceProvidor aboutServiceProvidor = new AboutServiceProvidor();
                            aboutServiceProvidor.setAboutUs(aboutProvidor.getJSONObject(i).getString("About Us"));
                            aboutProvidorList.add(aboutServiceProvidor);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    view.hideLoader();
                    if (aboutProvidorList.size()>0)
                    view.setServiceProviderAbout(aboutProvidorList);
                    else
                        view.serviceProvidorError();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.hideLoader();
            }
        });
        
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


}
