package com.marvel.android.a1000salama.Login;

import org.json.JSONException;
import org.json.JSONObject;

import APIClient.ApiClient;
import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 10/12/17.
 */

public class LoginPresneterImpl implements LoginPresenter  , ApiInterface{

    ApiClient apiInterface;
    @Override
    public int RequestLogin(String UserName, String Password) {
        JSONObject AuthBody = new JSONObject();
        try {
            AuthBody.put("P1" , UserName);
            AuthBody.put("P2" , Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        login("WS2/",AuthBody.toString(), ServicesConnection.CONTENT_TYPE);
        return 0;
    }

    @Override
    public Call<String> login(String webserviceNumber, String body, String content_type) {




        Call<String> QueryCall = ServicesConnection.GetService().login("WS2",body.toString(), ServicesConnection.CONTENT_TYPE);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

              String Body =   response.body();
                if (response.isSuccessful()) {

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
    public Call<String> getSystemMessages() {
        return null;
    }
}
