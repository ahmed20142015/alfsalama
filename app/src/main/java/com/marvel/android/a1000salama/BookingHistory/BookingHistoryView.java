package com.marvel.android.a1000salama.BookingHistory;

import java.util.ArrayList;

import Model.RequestItem;

/**
 * Created by ahmed on 06/02/18.
 */

public interface BookingHistoryView {



    void showLoader();
    void hideLoader();
    void setBookingRequestsHistoryList(ArrayList<RequestItem> RequestsList);

}
