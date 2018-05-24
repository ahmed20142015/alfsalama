package com.marvel.android.a1000salama.BookingHistory;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.RequestItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 06/02/18.
 */

public class BookingPresnterimpl implements BooKingHistoryPresneter , ApiInterface {
    BookingHistoryView BookingHistoryView;
    ArrayList<RequestItem> RequestsList = new ArrayList<>();
    int PtiantID;


    @Override
    public int GetMyBookRequests(int patientID) {

        String Reqeust = "{\n" +
                "\n" +
                "\"P1\":"+patientID+"" +
                "\n" +
                "\n" +
                "}";
        getBookingHistory("WS11",Reqeust,ServicesConnection.CONTENT_TYPE);
        BookingHistoryView.showLoader();
        return 0;
    }

    @Override
    public void setView(BookingHistoryView BookingHistoryView) {
        this.BookingHistoryView = BookingHistoryView;

    }

    @Override
    public int RateService(int patientID, String ServicProviderID, String Comment, String Rating, String BookID
    ,String totalMoney) {

        String BookServiceBody= "{";

        BookServiceBody +="\"P1\":"+patientID+" ,";
        PtiantID = patientID;

        if(ServicProviderID.equals(""))
        {

            BookServiceBody +="\"P2\":null ,";

        }
        else
            BookServiceBody +="\"P2\":"+ServicProviderID+" ,";

        if(Comment.equals(""))
        {

            BookServiceBody +="\"P3\":null ,";

        }
        else
            BookServiceBody +="\"P3\":\""+Comment+"\",";
        if(Rating.equals(""))
        {
            BookServiceBody +="\"P4\":null ,";


        }
        else {

            BookServiceBody +="\"P4\":"+Rating+" ,";
        }
        if(BookID.equals(""))
        {
            BookServiceBody +="\"P5\":null,";

        }
        else
            BookServiceBody +="\"P5\":"+BookID+",";
        if(totalMoney.equals(""))
        {
            BookServiceBody += "\"P6\":null";

        }
        else
            BookServiceBody +="\"P6\":"+totalMoney+"}";




        rateService(BookServiceBody,ServicesConnection.CONTENT_TYPE);



        return 0;
    }

    @Override
    public Call<String> getBookingHistory(String webserviceNumber, String body, String content_type) {

        Call<String> QueryCall = ServicesConnection.GetService().getBookingHistory("WS12",body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {
                    //P1OUT

                    try {
                      //  Body = Body.substring(18,Body.length()-2).trim();

                        JSONArray responCodeObj = new JSONArray(Body.trim());
                        for(int i  = 0 ; i < responCodeObj.length() ; i++)
                        {

                            RequestItem req = new RequestItem();
                            req.setID(responCodeObj.getJSONObject(i).getInt("ID"));
                            req.setBranchID(responCodeObj.getJSONObject(i).getInt("Service_providor_branch_id"));
                            req.setBranchName(responCodeObj.getJSONObject(i).getString("Service_providor_branch_name"));
                            req.setPatientID(responCodeObj.getJSONObject(i).getInt("Patient_id"));
                            req.setStatusName(responCodeObj.getJSONObject(i).getString("Status_Name"));
                            req.setMoneyPaid(responCodeObj.getJSONObject(i).getString("Money_paid"));
                            try {
                                req.setRatingFalg(responCodeObj.getJSONObject(i).getString("Rated_Flag"));
                            }
                            catch (Exception e)
                            {
                                req.setRatingFalg("N");
                            }
                            try {
                                req.setDate(responCodeObj.getJSONObject(i).getString("Servide_date"));
                                req.setMobileOfOtherPerson(responCodeObj.getJSONObject(i).getString("Mobile_Of_Other_Person"));
                                req.setNameOfOtherPerson(responCodeObj.getJSONObject(i).getString("Mobile_Of_Other_Person"));

                            }
                            catch (Exception e)
                            {

                            }
                            RequestsList.add(req);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                      BookingHistoryView.hideLoader();
                    BookingHistoryView.setBookingRequestsHistoryList(RequestsList);

                }
                else {
                    BookingHistoryView.hideLoader();
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());
                BookingHistoryView.hideLoader();
            }
        });


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
        Call<String> QueryCall = ServicesConnection.GetService().rateService(body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {
                    //P1OUT
                  // GetMyBookRequests(PtiantID);

                }




            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());

            }
        });
             return  null;
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


}
