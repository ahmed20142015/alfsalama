package APIClient;

import Model.ServiceSupplier;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ahmed on 10/12/17.
 */

public interface ApiInterface {


    @POST("alf/{path}/")
    Call<String> login(@Path("path") String webserviceNumber, @Body String body, @Header("Content-Type") String content_type);


    @POST("alf/{path}/")
    Call<String> Registration(@Path("path") String webserviceNumber, @Body String body, @Header("Content-Type") String content_type);

    @GET("alf/WS17/")
    Call<String> getSystemMessages();

    @POST("alf/{path}/")
    Call<String> getAllServiceProvider(@Path("path") String webserviceNumber, @Body String body, @Header("Content-Type") String content_type);


    @POST("alf/{path}/")
    Call<String> BookService(@Path("path") String webserviceNumber, @Body String body, @Header("Content-Type") String content_type);



    @POST("alf/{path}/")
    Call<String> getBookingHistory(@Path("path") String webserviceNumber, @Body String body, @Header("Content-Type") String content_type);


    @GET("alf/WS7/")
    Call<String> getServices();





    @GET("alf/WS8/")
    Call<String> getGovernates();



    @GET("alf/WS9/")
    Call<String> getCities();



    @GET("alf/WS10/")
    Call<String> getAreas();



    @POST("alf/WS21/")
    Call<String> getCat(@Body String body, @Header("Content-Type") String content_type);



    @POST("alf/WS6/")
    Call<String> rateService(@Body String body, @Header("Content-Type") String content_type);


    @POST("alf/WS15/")
    Call<String> GetComments( @Body String body, @Header("Content-Type") String content_type);


    @POST("alf/WS23/")
    Call<String> GetBranchServices( @Body String body, @Header("Content-Type") String content_type);


    @POST("alf/WS22/")
    Call<String> GetBrnchrates( @Body String body, @Header("Content-Type") String content_type);

    @POST("alf/WS24/")
    Call<String> GetAboutUsServiceProvidor(@Body String body, @Header("Content-Type") String content_type);

    @POST("alf/WS13/")
    Call<String> sendToUs(@Body String body, @Header("Content-Type") String content_type);

    @POST("alf/WS14/")
    Call<String> getOldTicks(@Body String body, @Header("Content-Type") String content_type);

    @FormUrlEncoded
    @POST("index.php")
    Call<String> uploadBookingPhotos(@Field("P1") int P1, @Field("P2") int P2,@Field("P3") String P3,
                                     @Field("P4") String P4,@Field("P5") String P5);


    @POST("alf/WS20/")
    Call<String> editUserData(@Body String body, @Header("Content-Type") String content_type);

    @GET("alf/WS25/")
    Call<ServiceSupplier> getServiceSuppliers();

}
