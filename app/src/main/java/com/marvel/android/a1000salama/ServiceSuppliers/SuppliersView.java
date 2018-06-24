package com.marvel.android.a1000salama.ServiceSuppliers;

import java.util.ArrayList;

import Model.ServiceSupplier;

/**
 * Created by ahmedpc on 23/6/2018.
 */

public interface SuppliersView {

    void showLoader();
    void hideLoader();
    void setSuppliers(ArrayList<ServiceSupplier.Supplier>suppliers);
    void setSuppliersError();

}
