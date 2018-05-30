package com.marvel.android.a1000salama.Registartion;

import com.marvel.android.a1000salama.ServicesProviderInfo.ServiceProviderInfo;

import Model.ServiceProidveritem;

/**
 * Created by ahmed on 14/12/17.
 */

public interface RegistartionView {

    void showLoader();
    void hideLoader();
    void edEmail();
    void edPassword();
    void btnReg();
    void imgPassView();
    void showalert(String Message);
    void NavigateToHome(int ID);
    void requestRegistartion(String firstName, String secondName, String lastName, String mobileNumber, String idNumber, String email, String password, String UserName, String DateOfBirth, String Gender);
    void requestWaring(String string);
    String getFirstName();
    String getSecondName();
    String getSurName();
    String getMobileNumber();
    String getNationalID();
    String getEmail();
    String getPass();
    String getConfirmPass();
    String getDateOFBirth();
    String getGender();
    void showErrorInputs();



}
