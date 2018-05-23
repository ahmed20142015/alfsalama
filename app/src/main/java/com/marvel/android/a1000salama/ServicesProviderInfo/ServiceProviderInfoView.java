package com.marvel.android.a1000salama.ServicesProviderInfo;

import java.util.ArrayList;

import Model.AboutServiceProvidor;

/**
 * Created by ahmedpc on 22/5/2018.
 */

public interface ServiceProviderInfoView {
    void showLoader();
    void hideLoader();
    void setServiceProviderAbout(ArrayList<AboutServiceProvidor> aboutUs);
    void serviceProvidorError();
}
