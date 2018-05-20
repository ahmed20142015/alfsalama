package com.marvel.android.a1000salama.BookingFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 02/02/18.
 */


public class BookingPersnterImpl implements  BookingPresneter , ApiInterface {

    BookingFragment BookingFragViwe ;
    ArrayList<Services> ServicesList  =  new ArrayList<>();
    int ResponseCode = -1;
    @Override
    public void setView(BookingFragment BookingFragment) {
        this.BookingFragViwe = BookingFragment;
    }

    @Override
    public int BookService(int patientID, String otherPersonName, String otherPersonMobileNumber, ArrayList<Integer> servicesList, String comments, int branchID) {

        String BookServiceBody= "{";

            BookServiceBody +="\"P1\":"+patientID+" ,";
        if(otherPersonName.equals(""))
        {
            BookServiceBody +="\"P2\":null ,";


        }
        else
            BookServiceBody +="\"P2\":\""+otherPersonName+"\" ,";

        if(otherPersonMobileNumber.equals(""))
        {

            BookServiceBody +="\"P3\":null ,";

        }
        else
            BookServiceBody +="\"P3\":"+otherPersonMobileNumber+" ,";
        if(servicesList.size()==0)
        {
            BookServiceBody +="\"P4\":null ,";


        }
        else {

            BookServiceBody += "\"P4\": [";
            for(int i = 0 ; i <servicesList.size() ; i++)
            {
                if(i != servicesList.size()-1)
                BookServiceBody += servicesList.get(i) + ",";
                else
                    BookServiceBody += servicesList.get(i) ;
            }
            BookServiceBody += "],";
        }
        if(comments.equals(""))
        {
            BookServiceBody +="\"P5\":null ,";

        }
        else
            BookServiceBody +="\"P5\":\""+comments+"\",";


        BookServiceBody +="\"P6\":"+branchID;


        BookServiceBody +="}";
        BookingFragViwe.showLoader();
        BookService("WS11",BookServiceBody,ServicesConnection.CONTENT_TYPE);
        return 0;
    }

    @Override
    public void getAllServices() {
        BookingFragViwe.showLoader();
        getServices();

    }


    @Override
    public Call<String> BookService(String webserviceNumber, String body, String content_type) {
        Call<String> QueryCall = ServicesConnection.GetService().BookService("WS11",body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {
                      //P1OUT


                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        ResponseCode = responCodeObj.getInt("P1OUT");
                        if(ResponseCode > -1)
                        {

                            BookingFragViwe.NavigateToSPPAge(ResponseCode);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    BookingFragViwe.hideLoader();
                    //viwe.setSPList(ServiceProivderList);

                }
                else {
                    BookingFragViwe.hideLoader();
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());
                BookingFragViwe.hideLoader();
            }
        });

        //ServicesConnection.GetService().getAllServiceProvider("WS4",body.toString(), ServicesConnection.CONTENT_TYPE);


        return  null;
    }

    @Override
    public Call<String> getBookingHistory(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getServices() {

        Call<String> QueryCall = ServicesConnection.GetService().getServices();
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String Body =   response.body();
                if (response.isSuccessful()) {


                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        JSONArray servicesArr= responCodeObj.getJSONArray("items");
                        for(int i = 0 ; i <  servicesArr.length();i++) {

                            Services service = new Services();
                            service.setID(servicesArr.getJSONObject(i).getInt("id"));
                            service.setName(servicesArr.getJSONObject(i).getString("name_en"));

                            ServicesList.add(service);
                        }


                        BookingFragViwe.hideLoader();
                        BookingFragViwe.setServiceList(ServicesList );


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


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

}