package com.marvel.android.a1000salama.BookingFragment;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.PhotoConnection;
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
    String lanuage="ar";
    @Override
    public void setView(BookingFragment BookingFragment) {
        this.BookingFragViwe = BookingFragment;
    }

    public void onBookClick(){


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
    public int BookPhotos(int patientID, int bookId, String firPhoto, String secPhoto, String thirPhoto) {
        uploadBookingPhotos(patientID,bookId,firPhoto,secPhoto,thirPhoto);
        return 0;
    }

    @Override
    public void getAllServices(String lang) {
        BookingFragViwe.showLoader();
        lanuage=lang;
        getServices();
    }


    @Override
    public Call<String> BookService(String webserviceNumber, String body, String content_type) {
        Call<String> QueryCall = ServicesConnection.GetService().BookService("WS11",body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                Log.w("responseBody",Body);
                if (response.isSuccessful()) {
                      //P1OUT


                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        ResponseCode = responCodeObj.getInt("P1OUT");
                        if(ResponseCode > -1)
                        {
                            BookingFragViwe.NavigateToSPPAge(ResponseCode);
                            BookingFragViwe.sendBookingPhotos(ResponseCode);
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
                    ServicesList.clear();
                    try {
                        JSONObject responCodeObj = new JSONObject(Body);
                        JSONArray servicesArr= responCodeObj.getJSONArray("items");
                        for(int i = 0 ; i <  servicesArr.length();i++) {

                            Services service = new Services();
                            service.setID(servicesArr.getJSONObject(i).getInt("id"));
                            if (lanuage.equals("en"))
                            service.setName(servicesArr.getJSONObject(i).getString("name_en"));
                            else if (lanuage.equals("ar"))
                            service.setName(servicesArr.getJSONObject(i).getString("name_ar"));

                            ServicesList.add(service);
                        }


                        BookingFragViwe.hideLoader();
                        BookingFragViwe.setServiceList(ServicesList,lanuage);


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
        Call<String> QueryCall = PhotoConnection.GetPhotoService().uploadBookingPhotos(P1,P2,P3,P4,P5);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body().toString();
                int counter = 0;
                if (response.isSuccessful()){
                    Log.w("photoresponse",body);
                    try {
                        JSONObject object = new JSONObject(body);
                        if(object.getInt("P3") == 200)
                            counter++;
                        if(object.getInt("P4") == 200)
                            counter++;
                        if(object.getInt("P5") == 200)
                            counter++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    BookingFragViwe.successUpload(counter);
                }
             }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w("photoresponse",t.getCause());
            }
        });
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
