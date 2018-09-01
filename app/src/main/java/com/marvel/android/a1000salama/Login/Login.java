package com.marvel.android.a1000salama.Login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.iid.FirebaseInstanceId;
import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Registartion.Registration;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity implements LoginView, GoogleApiClient.OnConnectionFailedListener {

    FlipProgressDialog progressDialog;
    LoginPresneterImpl loginPresenter;
    Button RegistrationBtn, LoginBtn, retrivePasswordBtn,loginAsVisitor;
    private EditText username, paasword;
    AppCompatCheckBox remberMe;
    private SignInButton signInWithGoogle;
    private LoginButton signInWithFacebook;
    private GoogleApiClient googleApiClient;
    private final static int googleRequest = 900;
    CallbackManager callbackManager;
    AlertDialog alertDialog;

    LoginView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        final Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        final Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }


        loginPresenter = new LoginPresneterImpl();

        loginPresenter.setView(this);
        RegistrationBtn = findViewById(R.id.RegBtn);
        LoginBtn = findViewById(R.id.loginBtn);
        username = findViewById(R.id.username);
        paasword = findViewById(R.id.paasword);
        remberMe = findViewById(R.id.remberme);
        loginAsVisitor = findViewById(R.id.login_as_visitor);
        retrivePasswordBtn = findViewById(R.id.retrive_password);
        signInWithGoogle = findViewById(R.id.login_with_google);
        signInWithFacebook = findViewById(R.id.login_with_facebook);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        callbackManager = CallbackManager.Factory.create();


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

        loginAsVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Home.class));
            }
        });

        retrivePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.retreive_password, null);
                dialogBuilder.setView(dialogView);

                TextView exit = dialogView.findViewById(R.id.exit);
                final EditText enteredEmail = dialogView.findViewById(R.id.entered_email);
                Button retrivePassBtn = dialogView.findViewById(R.id.retvire_pass_btn);

                alertDialog = dialogBuilder.create();
                alertDialog.setCancelable(true);
                alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                alertDialog.show();

                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                retrivePassBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (Utils.isInternetOn(Login.this)) {

                            String email  = enteredEmail.getText().toString().trim();

                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                showalert("البريد الإلكتروني غير صحيح");

                            }
                            else
                            loginPresenter.retrivePassword(email);

                        } else {
                            new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("خطأ")
                                    .setContentText("من فضلك تأكد من الإتصال بالإنترنت")
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


                    }
                });


            }
        });

        RegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.onLoginClicked();

            }
        });

        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        signInWithFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //   Toast.makeText(Login.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.i("RESAULTS : ", object.getString("email"));
                            Log.i("RESAULTS : ", object.toString());
                            //   Toast.makeText(Login.this, "name: "+object.getString("name")+"\n email: "+object.getString("email"), Toast.LENGTH_SHORT).show();

                            loginPresenter.loginWithSocial(object.getString("email"));
                        } catch (Exception e) {
                            new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("خطأ")
                                    .setContentText("لا يمكن الحصول على الايميل من فضلك انشأ حساب جديد")
                                    .setConfirmText("تم")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            // reuse previous dialog instance
                                            sDialog.dismiss();
                                        }
                                    })
                                    .show();                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email,picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Login Cancled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Login.this, "Login error", Toast.LENGTH_SHORT).show();
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
                .setContentText(Message)
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

        if (remberMe.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences("RemeberMe", MODE_PRIVATE).edit();
            editor.putInt("id", ID);
            editor.apply();
        }
        Intent i = new Intent(Login.this, Home.class);
        startActivity(i);
        finish();
    }

    @Override
    public void requestLogin(String userName, String Password) {
        loginPresenter.RequestLogin(userName, Password);
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

    @Override
    public void passwordRetrived() {
        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("حسناً")
                .setContentText("تم إرسال الرقم السري للايميل")
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
    public void errorRetrived() {
        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText("خطأ فى إرسال الرقم السري")
                .setConfirmText("تم")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();
                        alertDialog.dismiss();
                    }
                })
                .show();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, googleRequest);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            // Toast.makeText(this, "name: "+name+"\n email: "+email, Toast.LENGTH_SHORT).show();
            if(!email.equalsIgnoreCase(null)) {
                loginPresenter.loginWithSocial(email);
                updateUI(true);
            }
            else {
                new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("خطأ")
                        .setContentText("لا يمكن الحصول على الايميل من فضلك انشأ حساب جديد")
                        .setConfirmText("تم")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance
                                sDialog.dismiss();
                                alertDialog.dismiss();
                            }
                        })
                        .show();
            }
        } else
            updateUI(false);

    }

    private void updateUI(Boolean isLogin) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == googleRequest) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
