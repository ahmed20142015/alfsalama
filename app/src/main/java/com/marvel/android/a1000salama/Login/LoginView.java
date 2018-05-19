package com.marvel.android.a1000salama.Login;

/**
 * Created by ahmed on 10/12/17.
 */

public interface LoginView {

    void showLoader();
    void hideLoader();
    String edEmail();
    String edPassword();
    void chRemember();
    void chKeepMeLogin();
    void btnLogin();
    void imgPassView();
    void txtForgetPass();
    void showalert(String Message);
    void NavigateToHome(int ID);
    void requestLogin(String userName , String Password);
    void requestWaring(String string);
    void showUserNameError(int resId);
    void showPasswordError(int resId);

}
