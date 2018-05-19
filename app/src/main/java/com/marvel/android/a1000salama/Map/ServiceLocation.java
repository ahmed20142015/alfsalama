package com.marvel.android.a1000salama.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.marvel.android.a1000salama.R;

public class ServiceLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String Xcor , Ycor , Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Xcor =getIntent().getExtras().getString("X");
        Ycor =getIntent().getExtras().getString("Y");
        Name =  getIntent().getExtras().getString("Name");
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng( Double.parseDouble( Xcor), Double.parseDouble( Ycor));
        mMap.addMarker(new MarkerOptions().position(sydney).title(Name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //Move the camera to the user's location and zoom in!
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }
}
