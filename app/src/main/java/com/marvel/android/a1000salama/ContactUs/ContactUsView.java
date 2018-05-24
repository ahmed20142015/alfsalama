package com.marvel.android.a1000salama.ContactUs;

/**
 * Created by ahmedpc on 24/5/2018.
 */

public interface ContactUsView {
    void showLoader();
    void hideLoader();
    void successfullySend();
    void errorWhileSend();
    void setEmpty();
}
