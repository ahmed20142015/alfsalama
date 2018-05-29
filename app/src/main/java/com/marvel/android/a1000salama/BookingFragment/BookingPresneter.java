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
    public int BookPhotos (int patientID , int bookId ,
                            String firPhoto,String secPhoto , String thirPhoto);

    public  void getAllServices(String lanuage);


}
