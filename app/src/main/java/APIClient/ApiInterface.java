package APIClient;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @GET("alf/WS17/")
    Call<String> getSystemMessages();



}
