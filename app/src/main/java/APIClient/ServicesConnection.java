package APIClient;

/**
 * Created by ahmed on 10/12/17.
 */

public class ServicesConnection {


    private static ApiInterface apiInterface = null;
    public static final String PATCH = "PATCH";
    public static final String CONTENT_TYPE = "application/json";
    public static final String BASE_URL = "https://a12c.enciva.com/apex/salama/";



    private ServicesConnection(){}

    public static ApiInterface GetService(){
        if(apiInterface==null){
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
        }

        return  apiInterface;
    }





}
