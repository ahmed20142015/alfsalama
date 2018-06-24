package com.marvel.android.a1000salama.EditAccount;

/**
 * Created by ahmedpc on 20/6/2018.
 */

public interface EditUserAccountPresnter {

    public void setView(EditUserAccountViwe view);
    public int RequestEditAccount (int patientID,String firstName
                                    , String secondName , String lastName ,
                                    String mobileNumber ,
                                    String email , String password ,
                                    String UserName
    );

}
