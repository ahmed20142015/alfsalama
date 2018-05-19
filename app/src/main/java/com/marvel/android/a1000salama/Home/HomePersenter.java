package com.marvel.android.a1000salama.Home;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by ahmed on 26/01/18.
 */

public interface HomePersenter {
    public void setView(HomeViwe homeViwe);

    public int GetAllServicesProivders (String BranchName , String Gover_id ,
                                        String city_id , String earea_id, ArrayList<String> series_ids
    , String catID);

    public int GetGovrnates (Context context);

    public int GetCities (Context context);

    public int GetAreas (Context context);


    public int GetCats (Context context);
}
