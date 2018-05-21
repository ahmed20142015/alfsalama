package com.marvel.android.a1000salama.BookingFragment;

import java.util.ArrayList;

/**
 * Created by ahmed on 02/02/18.
 */

public interface BookingPresneter {

    public void setView(BookingFragment BookingFragment);

    public int BookService (int patientID , String otherPersonName ,
                            String otherPersonMobileNumber, ArrayList<Integer> servicesList ,
                             String comments , int branchID);
    public  void getAllServices(String lanuage);


}
