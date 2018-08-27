package com.marvel.android.a1000salama.Login;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import APIClient.ApiClient;
import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.ServiceSupplier;
import Model.SystemMessage;
import cn.pedant.SweetAlert.SweetAlertDialog;
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

    public void onLoginClicked(){
        String email = loginView.edEmail();
        String password = loginView.edPassword();
        if (email.isEmpty()){
            loginView.showUserNameError(R.string.user_name_error);
            showDialog();
        }
        else if(password.isEmpty()){
            loginView.showPasswordError(R.string.password_error);
            showDialog();
        }
        else  if (Utils.isInternetOn(loginView))
        {
            int response = RequestLogin(loginView.edEmail(),
                    loginView.edPassword());

        }

        else {
            new SweetAlertDialog(loginView, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("خطأ")
                    .setContentText( "من فضلك تأكد من الإتصال بالإنترنت")
                    .setConfirmText("تم")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            // reuse previous dialog instance
                            sDialog.dismiss();


                        }
                    })
                    .show();
        }
    }

    @Override
    public int RequestLogin(String UserName, String Password) {

        loginView.showLoader();
        JSONObject AuthBody = new JSONObject();
        try {
            AuthBody.put("P1" , UserName);
            AuthBody.put("P2" , Password);
            AuthBody.put("P3", FirebaseInstanceId.getInstance().getToken());
            Log.w("response",FirebaseInstanceId.getInstance().getToken());
            AuthBody.put("P4" , "N");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        login("WS2",AuthBody.toString(), ServicesConnection.CONTENT_TYPE);
        return ResponseCode;
    }

    @Override
    public int loginWithSocial(String email) {
        loginView.showLoader();
        JSONObject AuthBody = new JSONObject();
        try {
            AuthBody.put("P1" , email);
            AuthBody.put("P2" , null);
            AuthBody.put("P3", FirebaseInstanceId.getInstance().getToken());
            AuthBody.put("P4" , "Y");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        login("WS2",AuthBody.toString(), ServicesConnection.CONTENT_TYPE);
        return ResponseCode;
    }

    @Override
    public int retrivePassword(String email) {
        loginView.showLoader();
        JSONObject AuthBody = new JSONObject();
        try {
            AuthBody.put("P1" , email);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        retrivePassword(AuthBody.toString(), ServicesConnection.CONTENT_TYPE);

        return 0;
    }

    @Override
    public Call<String> retrivePassword(String body, String content_type) {

        Call<String> call = ServicesConnection.GetService().retrivePassword(body,ServicesConnection.CONTENT_TYPE);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();

                JSONObject responCodeObj = null;

                if (response.isSuccessful()){


                    try {
                        responCodeObj = new JSONObject(Body);
                        int respons =      responCodeObj.getInt("P1OUT");
                        if (respons > 0){
                            loginView.hideLoader();
                            loginView.passwordRetrived();
                        }

                        else {

                            loginView.hideLoader();
                            loginView.errorRetrived();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                else {
                    loginView.hideLoader();
                    loginView.errorRetrived();
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                loginView.hideLoader();
                loginView.errorRetrived();

            }
        });

        return null;
    }

    @Override
    public Call<String> login(String webserviceNumber, String body, String content_type) {


       String Return = "-1";

        Call<String> QueryCall = ServicesConnection.GetService().login("WS2",body.toString(), ServicesConnection.CONTENT_TYPE);
        Log.w("content",ServicesConnection.CONTENT_TYPE);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                Log.w("response",Body+"");
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
        return null;
    }

    @Override
    public Call<ServiceSupplier> getServiceSuppliers() {
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

    private void showDialog(){
        new SweetAlertDialog(loginView, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("خطأ")
                .setContentText( "من فضلك استكمل البيانات الفارغة")
                .setConfirmText("تم")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();


                    }
                })
                .show();

    }

}
