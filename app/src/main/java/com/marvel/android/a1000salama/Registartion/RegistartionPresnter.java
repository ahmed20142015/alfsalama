package com.marvel.android.a1000salama.Registartion;

/**
 * Created by ahmed on 14/12/17.
 */

public interface RegistartionPresnter {

    public void setView(Registration registartionView);
    public int RequestRegistration (String firstName
            , String secondName , String lastName ,
                                    String mobileNumber ,String idNumber,
                                    String email , String password ,
                                    String UserName , String DateOfBirth ,
                                    String Gender
                                    );


}
