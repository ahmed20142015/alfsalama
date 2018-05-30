package com.marvel.android.a1000salama.ServicesProviderInfo;

import com.marvel.android.a1000salama.Login.Login;

/**
 * Created by ahmedpc on 22/5/2018.
 */

public interface ServiceProviderInfoPresenter {
    public void setView(ServiceProviderInfoView view);
    public int RequestAboutServiceProvidor (int branchId);

}
