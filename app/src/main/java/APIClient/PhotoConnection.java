package APIClient;

/**
 * Created by ahmedpc on 28/5/2018.
 */

public class PhotoConnection {

    private static ApiInterface apiInterface = null;
    public static final String PHOTO_URL = "http://photo.salama1000.com/";
    public static ApiInterface GetPhotoService(){
        if(apiInterface==null){
            apiInterface = ApiPhoto.getPhotoClient().create(ApiInterface.class);
        }

        return  apiInterface;
    }

}
