package com.marvel.android.a1000salama.ServiceSuppliers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.R;
import com.squareup.picasso.Picasso;
import com.taishi.flipprogressdialog.FlipProgressDialog;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

import Model.ServiceSupplier;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;


public class SuppliersFragment extends BaseFragment implements SuppliersView {
    GoogleMap mGoogleMap;
    MapView mMapView;
    LocationManager locationManager;
    ArrayList<ServiceSupplier.Supplier> suppliers = new ArrayList<>();
    int index;

    SuppliersPresnterImpl presnter;
    FlipProgressDialog progressDialog;
    private OnFragmentInteractionListener mListener;

    public SuppliersFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SuppliersFragment newInstance(String param1, String param2) {
        SuppliersFragment fragment = new SuppliersFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suppliers, container, false);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        initMap();

        progressDialog = new FlipProgressDialog();
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.ic_hourglass_empty_white_24dp);
        imageList.add(R.drawable.ic_hourglass_full_white_24dp);
        progressDialog.setImageList(imageList);
        progressDialog.setOrientation("rotationY");
        progressDialog.setCancelable(false);
        progressDialog.setDimAmount(0.8f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressDialog.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
        } else {
            progressDialog.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }

        presnter = new SuppliersPresnterImpl();
        presnter.setView(this);

        presnter.getServiceSuppliers();

        return view;

    }

    private void initMap() {
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;

                LatLng s = new LatLng(30.068650, 31.246454);

                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(s, 7));

                mGoogleMap.setBuildingsEnabled(true);
//                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                        ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.setTrafficEnabled(true);
                mGoogleMap.getUiSettings().setMapToolbarEnabled(false);

            }
        });



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoader() {
        progressDialog.show(getActivity().getFragmentManager(), "l");
    }

    @Override
    public void hideLoader() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setSuppliers(ArrayList<ServiceSupplier.Supplier> suppliers) {
        this.suppliers= suppliers;


        for (int i= 0 ;i < suppliers.size();i++){



            ServiceSupplier.Supplier supplier = suppliers.get(i);
            LatLng latLng = new LatLng(supplier.getXCordinate(),supplier.getYCordinate());
            MarkerOptions marker = new MarkerOptions().position(
                    latLng).title(supplier.getSpName().toString()).snippet(i+"").icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_RED
                    ));

            // mGoogleMap.clear();
            mGoogleMap.addMarker(marker);

            mGoogleMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());

        }

        Toast.makeText(getActivity(), suppliers.size()+"", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setSuppliersError() {
        new SweetAlertDialog(this.getContext(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText("خطأ في رجوع المزودين")
                .setConfirmText("تم")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();
                    }
                })
                .show();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        public MarkerInfoWindowAdapter()
        {
        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker)
        {
            View v = getActivity().getLayoutInflater().inflate(R.layout.marker_dialog, null);
            TextView supplierName = v.findViewById(R.id.supplier_name);
            TextView supplierWorkHour = v.findViewById(R.id.supplier_work_hours);
            ImageView supplierLogo = v.findViewById(R.id.supplier_logo);
            RatingBar supplierRate = v.findViewById(R.id.supplier_rate);

            ServiceSupplier.Supplier supplier = suppliers.get(Integer.parseInt(marker.getSnippet()));

            if (supplier.getSpName() != null)
                supplierName.setText(supplier.getSpName());

            //    supplierWorkHour.setText(supplier.getOpeningHours().toString());

            supplierRate.setRating(supplier.getOverAllRating());


            if (supplier.getLogoUrl() != null){
                Picasso.with(getActivity()).load(supplier.getLogoUrl()).placeholder(
                        getActivity().getResources().getDrawable(R.drawable.error_looding))
                        .into(supplierLogo);

            }


            return v;
        }
    }
}
