package APIClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ahmedpc on 28/5/2018.
 */

public class ApiPhoto {
    private static Retrofit retrofit = null;

    private ApiPhoto() {
    }

    public static Retrofit getPhotoClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(PhotoConnection.PHOTO_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
