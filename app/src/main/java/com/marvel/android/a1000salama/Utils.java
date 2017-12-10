package com.marvel.android.a1000salama;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 10/12/17.
 */

public class Utils implements ApiInterface {


    @Override
    public Call<String> login(String webserviceNumber, String body, String content_type) {







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
}
