package com.marvel.android.a1000salama.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marvel.android.a1000salama.R;

public class Login extends AppCompatActivity implements LoginViwe {


    LoginPresneterImpl loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        loginPresenter = new LoginPresneterImpl();
        requestLogin("Abahaa","12345");
//        Utils utl = new Utils();
//        utl.getSystemMessages();

    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void edEmail() {

    }

    @Override
    public void edPassword() {

    }

    @Override
    public void chRemember() {

    }

    @Override
    public void chKeepMeLogin() {

    }

    @Override
    public void btnLogin() {

    }

    @Override
    public void imgPassView() {

    }

    @Override
    public void txtForgetPass() {

    }

    @Override
    public void requestLogin(String userName , String Password) {
       loginPresenter.RequestLogin(userName , Password);
    }

    @Override
    public void requestWaring(String string) {

    }


}
