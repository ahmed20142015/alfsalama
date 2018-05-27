package ClientComments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 05/05/18.
 */

public  class commentsPresneterImpl implements  commentsPresenter , ApiInterface {

   commentsVew commentsVew;

    @Override
    public void setView(ClientComments ClientComments) {


        this.commentsVew = ClientComments;
    }


    @Override
    public void getComments(int SPID) {
        String BookServiceBody= "{";

        BookServiceBody +="\"P1\":"+SPID+" ";


        BookServiceBody +="}";
       // commentsVew.showLoader();
        GetComments(BookServiceBody, ServicesConnection.CONTENT_TYPE);
    }

    @Override
    public Call<String> GetComments(String body, String content_type) {

        Call<String> QueryCall = ServicesConnection.GetService().GetComments(body,content_type);
        QueryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String Body =   response.body();
                if (response.isSuccessful()) {
                    //P1OUT


                    try {
                        JSONObject responCodeObj = new JSONObject ("{\n" +
                                "\"OLD_CONTACT\":\n" +
                                "[\n" +
                                "{\n" +
                                "\"Patient_name\":\"ali-ibrahim--\"\n" +
                                ",\"comment\":\"test1 comment\"\n" +
                                "}\n" +
                                ",{\n" +
                                "\"Patient_name\":\"\\u0650\\u0623\\u062D\\u0645\\u062F-\\u0628\\u0647\\u0627\\u0621 \\u0627\\u0644\\u062F\\u064A\\u0646-\\u0650\\u0623\\u062D\\u0645\\u062F-\"\n" +
                                ",\"comment\":\"comment\"\n" +
                                "}\n" +
                                "]\n" +
                                "\n" +
                                "}\n");
                      JSONArray commentarray = responCodeObj.getJSONArray("OLD_CONTACT");
                        ArrayList<Comment> comments = new ArrayList<>();
                        for(int i = 0 ; i  < commentarray.length();i++)
                        {
                            Comment comment = new Comment();
                            comment.setPatient_name(commentarray.getJSONObject(i).getString("Patient_name"));
                           comment.setComment( commentarray.getJSONObject(i).getString("comment"));
                           comments.add(comment);
                        }


                        commentsVew.setCommentsList(comments);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                 //   BookingFragViwe.hideLoader();
                    //viwe.setSPList(ServiceProivderList);

                }
                else {
                //    BookingFragViwe.hideLoader();
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // String Body = t.toString();
                System.out.print(toString());
            //    BookingFragViwe.hideLoader();
            }
        });
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
    public Call<String> getCat(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> rateService(String body, String content_type) {
        return null;
    }



}
