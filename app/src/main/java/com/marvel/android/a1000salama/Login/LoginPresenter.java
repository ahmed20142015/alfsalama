package com.marvel.android.a1000salama.Login;

/**
 * Created by ahmed on 10/12/17.
 */

public interface LoginPresenter {

    public void setView(Login loginView);
    public int RequestLogin (String UserName , String Password );
    public int loginWithSocial(String email);
}
