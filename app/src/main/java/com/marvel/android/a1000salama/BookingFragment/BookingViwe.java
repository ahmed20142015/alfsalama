package com.marvel.android.a1000salama.BookingFragment;

import java.util.ArrayList;

import Model.ServiceProidveritem;
import Model.Services;

/**
 * Created by ahmed on 02/02/18.
 */

public interface BookingViwe {


    void showLoader();
    void hideLoader();
    void setSPList(ArrayList<ServiceProidveritem> ServiceProivderList);
    void NavigateToSPPAge( int ResponseCode);
    void setServiceList(   ArrayList<Services> ServicesList );
}
