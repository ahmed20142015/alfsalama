package com.marvel.android.a1000salama.ContactUs;

/**
 * Created by ahmedpc on 24/5/2018.
 */

public interface ContactUsPresenter {
    public void setView(ContactUsView view);
    public int sendToUs (String subject,int patientID , String message,Integer replayToContactID );
}
