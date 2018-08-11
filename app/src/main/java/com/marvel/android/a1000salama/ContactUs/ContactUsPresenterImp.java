package com.marvel.android.a1000salama.ContactUs;

import org.json.JSONException;
import org.json.JSONObject;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.ServiceSupplier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmedpc on 24/5/2018.
 */

public class ContactUsPresenterImp implements ContactUsPresenter, ApiInterface {
    ContactUsView view;
    @Override
    public void setView(ContactUsView view) {
        this.view = view;
    }

    @Override
    public int sendToUs(String subject, int patientID, String message, Integer replayToContactID) {
        view.showLoader();
        JSONObject authBody = new JSONObject();
        try {
            authBody.put("P1",subject);
            authBody.put("P2",patientID);
            authBody.put("P3",message);
            authBody.put("P4",replayToContactID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendToUs(authBody.toString(),ServicesConnection.CONTENT_TYPE);
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
    public Call<String> getAllVersionList() {
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
        Call<String> QueryCall = ServicesConnection.GetService().sendToUs(body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        int responseResult = jsonObject.getInt("P1OUT");
                        if (responseResult == 200){
                            view.hideLoader();
                            view.successfullySend();
                            view.setEmpty();
                        }
                        else {
                            view.hideLoader();
                            view.errorWhileSend();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.hideLoader();
                view.errorWhileSend();
            }
        });
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
