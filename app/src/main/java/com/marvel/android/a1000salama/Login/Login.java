package com.marvel.android.a1000salama.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Registartion.Registration;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity implements LoginView {

    FlipProgressDialog progressDialog;
    LoginPresneterImpl loginPresenter;
    Button RegistrationBtn  , LoginBtn;
    private EditText username , paasword;
    AppCompatCheckBox remberMe;
    LoginView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        final Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }


        loginPresenter = new LoginPresneterImpl();

        loginPresenter.setView(this);
        RegistrationBtn  = findViewById(R.id.RegBtn);
        LoginBtn  = findViewById(R.id.loginBtn);
        username = findViewById(R.id.username);
        paasword = findViewById(R.id.paasword);
        remberMe = findViewById(R.id.remberme);
       // requestLogin("Abahaa","12345");

//        Utils utl = new Utils();
//        utl.getSystemMessages();

        progressDialog = new FlipProgressDialog();
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.ic_hourglass_empty_white_24dp);
        imageList.add(R.drawable.ic_hourglass_full_white_24dp);
        progressDialog.setImageList(imageList);
        progressDialog.setOrientation("rotationY");
        progressDialog.setCancelable(false);
        progressDialog.setDimAmount(0.8f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressDialog.setBackgroundColor(getColor(R.color.colorPrimary));
        } else {
            progressDialog.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }

        RegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.onLoginClicked();

            }
        });

    }

    @Override
    public void showLoader() {
        progressDialog.show(getFragmentManager(), "l");
    }

    @Override
    public void hideLoader() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public String edEmail() {
        return username.getText().toString();
    }

    @Override
    public String edPassword() {
        return paasword.getText().toString();
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
    public void showalert(String Message) {
        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText( Message)
                .setConfirmText("تم")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void NavigateToHome(int ID) {

       if(remberMe.isChecked() ) {
           SharedPreferences.Editor editor = getSharedPreferences("RemeberMe", MODE_PRIVATE).edit();
           editor.putInt("id",ID);
           editor.apply();
       }
        Intent i =new Intent(Login.this, Home.class);
        startActivity(i);
        finish();
    }

    @Override
    public void requestLogin(String userName , String Password) {
       loginPresenter.RequestLogin(userName , Password);
    }

    @Override
    public void requestWaring(String string) {

    }

    @Override
    public void showUserNameError(int resId) {
        username.setError(getString(resId));
    }

    @Override
    public void showPasswordError(int resId) {
        paasword.setError(getString(resId));
    }


}
