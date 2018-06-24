package com.marvel.android.a1000salama.ServiceSuppliers;

import java.util.ArrayList;

import APIClient.ApiInterface;
import APIClient.ServicesConnection;
import Model.ServiceSupplier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmedpc on 23/6/2018.
 */

public class SuppliersPresnterImpl implements SuppliersPresnter , ApiInterface {
    SuppliersView view;
    ArrayList<ServiceSupplier.Supplier> suppliers = new ArrayList<>();
    @Override
    public void setView(SuppliersView view) {
        this.view = view;
    }

    @Override
    public int getSuppliers() {
        getServiceSuppliers();
        view.showLoader();
        return 0;
    }


    @Override
    public Call<String> login(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> Registration(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getSystemMessages() {
        return null;
    }

    @Override
    public Call<String> getAllServiceProvider(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> BookService(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getBookingHistory(String webserviceNumber, String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getServices() {
        return null;
    }

    @Override
    public Call<String> getGovernates() {
        return null;
    }

    @Override
    public Call<String> getCities() {
        return null;
    }

    @Override
    public Call<String> getAreas() {
        return null;
    }

    @Override
    public Call<String> getCat(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> rateService(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetComments(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetBranchServices(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetBrnchrates(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> GetAboutUsServiceProvidor(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> sendToUs(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> getOldTicks(String body, String content_type) {
        return null;
    }

    @Override
    public Call<String> uploadBookingPhotos(int P1, int P2, String P3, String P4, String P5) {
        return null;
    }

    @Override
    public Call<String> editUserData(String body, String content_type) {
        return null;
    }

    @Override
    public Call<ServiceSupplier> getServiceSuppliers() {
        Call<ServiceSupplier> call = ServicesConnection.GetService().getServiceSuppliers();
        call.enqueue(new Callback<ServiceSupplier>() {
            @Override
            public void onResponse(Call<ServiceSupplier> call, Response<ServiceSupplier> response) {
                if (response.isSuccessful()){
//                    view.hideLoader();
                    for (int i=0;i<response.body().getItems().size();i++){
                        ServiceSupplier.Supplier supplier = new ServiceSupplier.Supplier();
                        supplier = response.body().getItems().get(i);
                        suppliers.add(supplier);
                    }

                    view.setSuppliers(suppliers);
                }

                else {
                    view.hideLoader();
                    view.setSuppliersError();
                }
            }

            @Override
            public void onFailure(Call<ServiceSupplier> call, Throwable t) {
                view.hideLoader();
                view.setSuppliersError();
            }
        });
        return null;
    }
}
