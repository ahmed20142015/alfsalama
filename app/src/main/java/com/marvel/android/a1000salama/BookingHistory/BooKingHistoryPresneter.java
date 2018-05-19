package com.marvel.android.a1000salama.BookingHistory;

/**
 * Created by ahmed on 06/02/18.
 */

public interface BooKingHistoryPresneter {


    // P1
  public int GetMyBookRequests (int patientID );

    public void setView(BookingHistoryView BookingHistoryView);

    public int RateService (int patientID , String ServicProviderID ,
                            String Comment , String Rating ,
                            String BookID, String totalMoney);

}



