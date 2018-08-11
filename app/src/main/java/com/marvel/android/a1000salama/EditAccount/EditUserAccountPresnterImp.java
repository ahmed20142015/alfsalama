package com.marvel.android.a1000salama.EditAccount;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.ServiceSupplier;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmedpc on 20/6/2018.
 */

public class EditUserAccountPresnterImp implements EditUserAccountPresnter , ApiInterface {
    EditUserAccountViwe viwe;
    @Override
    public void setView(EditUserAccountViwe view) {
        this.viwe = view;
    }

    @Override
    public int RequestEditAccount(int patientID, String firstName, String secondName, String lastName,
                                  String mobileNumber, String email, String password, String UserName) {
        viwe.showLoader();
        JSONObject authBody = new JSONObject();
        try {
                authBody.put("P1",patientID);

            if(!email.equalsIgnoreCase(""))
                authBody.put("P2",email);

            if(!password.equalsIgnoreCase(""))
                authBody.put("P3",password);

            if(!email.equalsIgnoreCase(""))
                authBody.put("P4",email);

            if(!firstName.equalsIgnoreCase(""))
                authBody.put("P5",firstName);

            if(!secondName.equalsIgnoreCase(""))
                authBody.put("P6",secondName);

            if(!lastName.equalsIgnoreCase(""))
                authBody.put("P7",lastName);

            if(!lastName.equalsIgnoreCase(""))
                authBody.put("P8",lastName);

            if(!mobileNumber.equalsIgnoreCase(""))
                authBody.put("P9",mobileNumber);

            Log.w("llllllllll",authBody.toString());

            editUserData(authBody.toString(),ServicesConnection.CONTENT_TYPE);

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        Call<String> call = ServicesConnection.GetService().editUserData(body,ServicesConnection.CONTENT_TYPE);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {


                    try {

                        JSONObject responCodeObj = new JSONObject(Body);
                        int code = responCodeObj.getInt("P1OUT");

                        if(code > 0)
                            viwe.successUpdate();

                        else
                            viwe.failUpdate();

                            viwe.hideLoader();

                    }
                    catch (JSONException e) {
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                viwe.failUpdate();
                viwe.hideLoader();
            }
        });

        return null;
    }

    @Override
    public Call<ServiceSupplier> getServiceSuppliers() {
        return null;
    }

}
