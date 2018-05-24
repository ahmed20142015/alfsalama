package com.marvel.android.a1000salama.Registartion;

import android.os.AsyncTask;

import com.marvel.android.a1000salama.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import APIClient.ApiClient;
import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.SystemMessage;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 14/12/17.
 */

public class RegistartionPresnterImpl implements RegistartionPresnter , ApiInterface {

    Registration registartionView ;
    int ResponseCode = -1;
    ApiClient apiInterface;
    Utils utils = new Utils();
    List<SystemMessage> SysMessageList = new ArrayList<>();
    @Override
    public void setView(Registration registartionView) {
        this.registartionView = registartionView;
    }

    public void onRegisterClicked(){
        String firstName = registartionView.getFirstName(),
                secondName = registartionView.getSecondName(),
                lastName = registartionView.getSurName(),
                mobileNumber = registartionView.getMobileNumber(),
                idNumber = registartionView.getNationalID(),
                email = registartionView.getEmail(),
                password = registartionView.getPass(),
                conFirmPass = registartionView.getConfirmPass(),
                UserName = registartionView.getEmail(),
                DateOfBirth = registartionView.getDateOFBirth(),
                Gender = registartionView.getGender();
        if(firstName.isEmpty()||secondName.isEmpty()||lastName.isEmpty()||mobileNumber.isEmpty()
                ||email.isEmpty()||password.isEmpty()||UserName.isEmpty()
                ||DateOfBirth.isEmpty()||Gender.isEmpty()){

            registartionView.showErrorInputs();
        }
        else if (!conFirmPass.equals(password))
        {
            showErrorMessage("كلمة المرور غير متطابقة");

        }

        else if ( !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                )
        {
            showErrorMessage("البريد الإلكتروني غير صحيح");

        }


        else if ( mobileNumber.length()!=11 || mobileNumber.charAt(0)!='0'|| mobileNumber.charAt(1)!='1')
        {
            showErrorMessage("رقم الهاتف غير صحيح ");

        }







        else {

            if(Utils.isInternetOn(registartionView)) {
                        RequestRegistration(firstName, secondName, lastName, mobileNumber, idNumber, email, password, UserName, DateOfBirth, Gender);
            }
            else
                showErrorMessage("من فضلك تأكد من الإتصال بالإنترنت");

        }
    }

    @Override
    public int RequestRegistration(String firstName,
                                   String secondName, String lastName, String mobileNumber,
                                   String idNumber, String email, String password, String UserName, String DateOfBirth, String Gender) {
        registartionView.showLoader();

        String RegRequestBody= "{";
        if(firstName.equals(""))
        {
            RegRequestBody +="\"P1\":null ,";

        }
        else
            RegRequestBody +="\"P1\":\""+firstName+"\" ,";
        if(secondName.equals(""))
        {
            RegRequestBody +="\"P2\":null ,";


        }
        else
            RegRequestBody +="\"P2\":\""+secondName+" \",";

        if(lastName.equals(""))
        {

            RegRequestBody +="\"P3\":null ,";

        }
        else
            RegRequestBody +="\"P3\":\""+lastName+" \",";
        if(mobileNumber.equals(""))
        {
            RegRequestBody +="\"P4\":null ,";


        }
        else
            RegRequestBody +="\"P4\":\""+lastName+"\" ,";
        if(mobileNumber.equals(""))
        {
            RegRequestBody +="\"P5\":null ";

        }
        else
            RegRequestBody +="\"P5\":\""+mobileNumber+"\",";

        if(idNumber.equals(""))
        {
            RegRequestBody +="\"P6\":null ,";

        }
        else
            RegRequestBody +="\"P6\":\""+idNumber+"\",";

        if(email.equals(""))
        {
            RegRequestBody +="\"P7\":null , ";

        }
        else
            RegRequestBody +="\"P7\":\""+email+"\",";
        if(password.equals(""))
        {
            RegRequestBody +="\"P8\":null ";

        }
        else
            RegRequestBody +="\"P8\":\""+password+"\",";
        if(email.equals(""))
        {
            RegRequestBody +="\"P9\":null ,";

        }
        else
            RegRequestBody +="\"P9\":\""+email+"\",";
        if(DateOfBirth.equals(""))
        {
            RegRequestBody +="\"P10\":null ,";

        }
        else
            RegRequestBody +="\"P10\":\""+DateOfBirth+"\",";

        if(Gender.equals(""))
        {
            RegRequestBody +="\"P10\":null ,";

        }
        else if(Gender.equals("M"))
            RegRequestBody +="\"P11\":\""+"M"+"\"";
        else if(Gender.equals("F"))
            RegRequestBody +="\"P11\":\""+"F"+"\"";

        RegRequestBody +="}";


        Registration("WS1/",RegRequestBody, ServicesConnection.CONTENT_TYPE);
        return 0;

    }

    @Override
    public Call<String> login(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> Registration(String webserviceNumber, String body, String content_type) {



        Call<String> QueryCall = ServicesConnection.GetService().Registration
                ("WS1",body.toString(), ServicesConnection.CONTENT_TYPE);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String Body =   response.body();
                if (response.isSuccessful()) {



                    try {

                        JSONObject responCodeObj = new JSONObject(Body);
                        ResponseCode = responCodeObj.getInt("p1out");

                        if(ResponseCode <0)
                        {



                            utils = new Utils(registartionView.getApplicationContext());
                            utils.getSystemMessage(ResponseCode+"");
                            utils.getSystemMessages();

                            SysMessageList=utils.getSystemMessage(ResponseCode+"");
                            if(SysMessageList.size()==0) {


                              //  new GetSystemMessages().execute();
                                registartionView.hideLoader();
                            }
                            else
                            {

                                registartionView.hideLoader();
                                registartionView.showalert(utils.getSystemMessage(ResponseCode+"").get(0)
                                        .getMessage());

                            }

                        }
                        else {
                            registartionView.NavigateToHome(ResponseCode);
                        }
                    } catch (JSONException e) {
                        registartionView.showalert("حدث خطأ في الاتصال , من فضلك أعد المحاولة");
                    }




                }

                registartionView.hideLoader();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());
                registartionView.hideLoader();
                registartionView.showalert("حدث خطأ في الاتصال , من فضلك أعد المحاولة وتأكد من الاتصال بالانترنت");

            }
        });

        return  null ;


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

    public class GetSystemMessages extends AsyncTask<Object, Object, List<SystemMessage>> {




        @Override
        protected List<SystemMessage> doInBackground(Object... params) {

            utils.getSystemMessages();
            return SysMessageList;

        }

        @Override
        protected void onPostExecute(List<SystemMessage> SysMessageList) {

            SysMessageList=utils.getSystemMessage(ResponseCode+"");
           try {
               registartionView.showalert(SysMessageList.get(0)
                       .getMessage());
           }
           catch (Exception e)
           {
            //   registartionView.showalert();

           }
        }
    }

    private void showErrorMessage(String message){
        new SweetAlertDialog(registartionView, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText(message)
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
