package com.marvel.android.a1000salama.Home;

import java.util.ArrayList;

import Model.Area;
import Model.Catoegry;
import Model.City;
import Model.Governrate;
import Model.ServiceProidveritem;

/**
 * Created by ahmed on 26/01/18.
 */

public interface HomeViwe {


    void showLoader();
    void hideLoader();
    void setSPList(ArrayList<ServiceProidveritem> ServiceProivderList);
    void setGovList(ArrayList<Governrate> governrates);
    void setCityList(ArrayList<City> cities);
    void setAreaList(ArrayList<Area> Areas);
    void NavigateToSPDeatials(ServiceProidveritem sp);
    void setCatList(ArrayList<Catoegry> catoegries);

}
