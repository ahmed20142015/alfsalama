package com.marvel.android.a1000salama.Login;

import android.os.AsyncTask;

import com.google.firebase.iid.FirebaseInstanceId;
import com.marvel.android.a1000salama.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import APIClient.ApiClient;
import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.SystemMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 10/12/17.
 */

public class LoginPresneterImpl implements LoginPresenter  , ApiInterface{
    Login loginView ;
    ApiClient apiInterface;
    int ResponseCode = -1;
    Utils utils = new Utils();
    List<SystemMessage>  SysMessageList = new ArrayList<>();

    @Override
    public void setView(Login loginView) {
        this.loginView = loginView;

    }

    @Override
    public int RequestLogin(String UserName, String Password) {

        loginView.showLoader();
        JSONObject AuthBody = new JSONObject();
        try {
            AuthBody.put("P1" , UserName);
            AuthBody.put("P2" , Password);
            AuthBody.put("P3", FirebaseInstanceId.getInstance().getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        login("WS2",AuthBody.toString(), ServicesConnection.CONTENT_TYPE);
        return ResponseCode;
    }



    @Override
    public Call<String> login(String webserviceNumber, String body, String content_type) {


       String Return = "-1";

        Call<String> QueryCall = ServicesConnection.GetService().login("WS2",body.toString(), ServicesConnection.CONTENT_TYPE);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {

                    try {

                        JSONObject responCodeObj = new JSONObject(Body);
                        ResponseCode =      responCodeObj.getInt("P1OUT");


                        if(ResponseCode <0)
                        {



                            utils = new Utils(loginView.getApplicationContext());
                            utils.getSystemMessage(ResponseCode+"");
                            utils.getSystemMessages();

                            SysMessageList=utils.getSystemMessage(ResponseCode+"");
                            if(SysMessageList.size()==0) {


                                new GetSystemMessages().execute();
                                loginView.hideLoader();
                            }
                            else
                            {

                                loginView.hideLoader();
                                loginView.showalert(utils.getSystemMessage(ResponseCode+"").get(0)
                                        .getMessage());

                            }

                            // loginView.showalert("خطأ في البيانات");
                        }
                        else {
                            loginView.NavigateToHome(ResponseCode);
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
                loginView.hideLoader();
            }
        });





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


    public class GetSystemMessages extends AsyncTask<Object, Object, List<SystemMessage>> {




        @Override
        protected List<SystemMessage> doInBackground(Object... params) {

             utils.getSystemMessages();
            return SysMessageList;

        }

        @Override
        protected void onPostExecute(List<SystemMessage> SysMessageList) {

          SysMessageList=utils.getSystemMessage(ResponseCode+"");


          if(SysMessageList.size()>0)
            loginView.showalert(utils.getSystemMessage(ResponseCode+"").get(0)
                    .getMessage());
          else
          {
              loginView.showalert("برجاء التأكد من البيانات");
          }
        }
    }


}
