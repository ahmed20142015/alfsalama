package com.marvel.android.a1000salama.Login;

 import com.marvel.android.a1000salama.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import APIClient.ApiClient;
import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;
 import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




/**
 * Created by ahmedpc on 19/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresneterImplTest {

    //https://riggaroo.co.za/retrofit-2-mocking-http-responses/
    @Mock
    ApiInterface apiInterface;

    @Before
    public void setUp() throws Exception {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
     }
    @Test
    public void testLoginSuccess()throws Exception{

       String AuthBody = "{\"P1\":\"01065042247\",\"P2\":\"ahmed123456\",\"P3\":"+"\"fmvfkvfkmkmfkvmfkvmfkvmfkvmfkm\""+"}";
       Call<String> QueryCall = ServicesConnection.GetService().login("WS2",
                AuthBody, ServicesConnection.CONTENT_TYPE);

        Response<String>response = QueryCall.execute();
        Assert.assertTrue("response is fail",response.isSuccessful());
        Assert.assertEquals("fail to login","{\"P1OUT\":75}", response.body());

    }

    @Test
    public void testWrongPass()throws Exception{

        String AuthBody = "{\"P1\":\"01065042247\",\"P2\":\"ahmed1234\",\"P3\":"+"\"fmvfkvfkmkmfkvmfkvmfkvmfkvmfkm\""+"}";
        Call<String> QueryCall = ServicesConnection.GetService().login("WS2",
                AuthBody, ServicesConnection.CONTENT_TYPE);

        Response<String>response = QueryCall.execute();
        Assert.assertTrue("response is fail",response.isSuccessful());
        Assert.assertEquals("fail to login","{\"P1OUT\":-24}", response.body());

    }

    @Test
    public void testWrongPhone()throws Exception{

        String AuthBody = "{\"P1\":\"010650422\",\"P2\":\"ahmed123456\",\"P3\":"+"\"fmvfkvfkmkmfkvmfkvmfkvmfkvmfkm\""+"}";
        Call<String> QueryCall = ServicesConnection.GetService().login("WS2",
                AuthBody, ServicesConnection.CONTENT_TYPE);

        Response<String>response = QueryCall.execute();
        Assert.assertTrue("response is fail",response.isSuccessful());
        Assert.assertEquals("fail to login","{\"P1OUT\":-23}", response.body());
    }

    @Test
    public void testRegistrationSuccess() throws Exception{

        String AuthBody = "{\"P1\":\"ahmed\",\"P2\":\"mohamed\",\"P3\":"+"\"elashry\","+"\"P4\":\"ahmed\",\"P5\":\"01000000000\",\"P6\":\"55555\","+
                "\"P7\":\"amg244444@gmail.com\",\"P8\":\"ahmed01022\",\"P9\":\"amg244444@gmail.com\","+"\"P10\":\"10/2/2001\",\"P11\":\"M\""+"}";

        String xx = "{\"P1\":\"ahmed\" ,\"P2\":\"mohamed  \",\"P3\":\"elashry  \",\"P4\":\"elashry \" ,\"P5\":\"01110000000\",\"P6\":null ,\"P7\":\"q.w12344@yahoo.com\",\"P8\":\"ahmed123456\",\"P9\":\"q.w12344@yahoo.com\",\"P10\":\"14-06-1994\",\"P11\":\"M\"}";

        Call<String> QueryCall = ServicesConnection.GetService().Registration("WS1",
                xx, ServicesConnection.CONTENT_TYPE);

        Response<String>response = QueryCall.execute();
        Assert.assertTrue("response is fail",response.isSuccessful());
        Assert.assertEquals("fail to login","{\"p1out\":79} ", response.body());

    }

//    @Test
//    public void showErrorMessageWhenUsernameIsEmpty() throws Exception {
//        when(view.edEmail()).thenReturn("");
//        loginPresneter.onLoginClicked();
//        verify(view).showUserNameError(R.string.user_name_error);
//    }
//
//    @Test
//    public void showErrorMessageWhenPasswordIsEmpty() throws Exception {
//        when(view.edPassword()).thenReturn("");
//        loginPresneter.onLoginClicked();
//        verify(view).showPasswordError(R.string.password_error);
//    }
//
//
//    @Test
//    public void shouldStartHomeActivityWhenUsernameAndPasswordAreCorrect() throws Exception {
//       when(presenter.RequestLogin(view.edEmail(),view.edPassword())).thenReturn(75);
//        loginPresneter.onLoginClicked();
//        verify(view).NavigateToHome(75);
//    }
//
//    @Test
//    public void shouldShowLoginErrorWhenUsernameAndPasswordAreInvalid() throws Exception {
//        when(presenter.RequestLogin(view.edEmail(),view.edPassword())).thenReturn(-23);
//        loginPresneter.onLoginClicked();
//        verify(view).showalert("الإيميل أو رقم الموبايل غير مسجل");
//    }
}