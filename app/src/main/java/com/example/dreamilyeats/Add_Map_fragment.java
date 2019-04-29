package com.example.dreamilyeats;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Add_Map_fragment extends Fragment implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private LatLng latLng;
    private Geocoder geocoder;
    List<Address> address;
    private String TAG = "add_map";
    private Button done;


    private double lat_agarwalCeater = 26.9407;
    private  double longt_agarwalCeater = 75.7987;

    private double lat_agarwalCeater1 = 26.9530;
    private  double longt_agarwalCeater1 = 75.7772;

    private double lat_Thaggu = 26.8501;
    private  double longt_Thaggu = 75.8122;

    private double lat_Topaz = 26.9206;
    private  double longt_Topaz = 75.7980;

    private double lat_kanji = 26.9217;
    private  double longt_Kanji = 75.7967;

    private double lat_cafe = 26.9167;
    private  double longt_cafe = 75.8069;

    private double lat_coffee = 26.9638;
    private  double longt_coffee = 75.7980;

    private double lat_khandelwal = 26.9231;
    private  double longt_khandelwal = 75.8006;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add__map_fragment, container, false);

        done = view.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Your_Cart.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            checkUserLocationPermission();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
        mapFragment.getMapAsync(this);

       /* locationManager= (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,  this);*/

        return  view;
    }

    private boolean checkUserLocationPermission() {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        } else {
            locationManager= (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,  this);
            return true;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        if (googleApiClient == null) {

                            buildGoogleApiClient();
                        }
                        map.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            buildGoogleApiClient();

            map.setMyLocationEnabled(true);


            LatLng Agarwal_Cateres = new LatLng(lat_agarwalCeater, longt_agarwalCeater);
            googleMap.addMarker(new MarkerOptions().position(Agarwal_Cateres).title("Agarwal Caterers-Shastri Nagar"));

            LatLng Agarwal_Cateres1 = new LatLng(lat_agarwalCeater1, longt_agarwalCeater1);
            googleMap.addMarker(new MarkerOptions().position(Agarwal_Cateres1).title("Agarwal Caterers-Vidhyadhar Nagar"));

            LatLng Thaggu_k_samose = new LatLng(lat_Thaggu, longt_Thaggu);
            googleMap.addMarker(new MarkerOptions().position(Thaggu_k_samose).title("Thaggu ke Samose"));

            LatLng Topaz_restaurant = new LatLng(lat_Topaz, longt_Topaz);
            googleMap.addMarker(new MarkerOptions().position(Topaz_restaurant).title("Topaz Restaurant"));

            LatLng kanji_sweets = new LatLng(lat_kanji, longt_Kanji);
            googleMap.addMarker(new MarkerOptions().position(kanji_sweets).title("Kanji Sweets"));

            LatLng cafe_kebabs = new LatLng(lat_cafe, longt_cafe);
            googleMap.addMarker(new MarkerOptions().position(cafe_kebabs).title("Cafe Kebabs"));

            LatLng coffee_day = new LatLng(lat_coffee, longt_coffee);
            googleMap.addMarker(new MarkerOptions().position(coffee_day).title("Cafe Coffee Day - Vidhyadhar nagar"));

            LatLng khandelwal_pavitra = new LatLng(lat_khandelwal, longt_khandelwal);
            googleMap.addMarker(new MarkerOptions().position(khandelwal_pavitra).title("Khandelwal Pavitra Bhojnalaya"));


        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getContext()).addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        Log.e("Fragment :", "Synchronized");
        Log.e("BuildGoogleApiClient  :" , "Google");


        googleApiClient.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        lastLocation = location;

        if(currentUserLocationMarker != null){

            currentUserLocationMarker.remove();
        }

        Log.e("OnLocationChanged  :" , "Google");

        latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        currentUserLocationMarker = map.addMarker(markerOptions);

        Log.e("Coordinates of My Loc :" , " " + lastLocation.getLatitude() +" " + lastLocation.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomBy(12));

        Polyline polyline = map.addPolyline(new PolylineOptions().clickable(true).add((latLng),new LatLng(lat_agarwalCeater, longt_agarwalCeater)).color(Color.BLUE));

        double dis=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_agarwalCeater, longt_agarwalCeater);
        Log.e(TAG, "distance from Agarwal Ceaters-Shastri nagar :" + String.valueOf(dis)+ "Km");

        double dis1=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_agarwalCeater1, longt_agarwalCeater1);
        Log.e(TAG, "distance from Agarwal Ceaters-Vidhyadhar nagar :" +String.valueOf(dis1)+ "Km");

        double dis2=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_Thaggu, longt_Thaggu);
        Log.e(TAG, "distance from Thaggu ke Samose :" + String.valueOf(dis2)+ "Km");

        double dis3=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_Topaz, longt_Topaz);
        Log.e(TAG, "distance from Topaz Restaurant :" + String.valueOf(dis3)+ "Km");

        double dis4=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_kanji, longt_Kanji);
        Log.e(TAG, "distance from Kanji sweets :" + String.valueOf(dis4)+ "Km");

        double dis5=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_cafe, longt_cafe);
        Log.e(TAG, "distance from Cafe Kebabsr :" + String.valueOf(dis5)+ "Km");

        double dis6=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_coffee, longt_coffee);
        Log.e(TAG, "distance from Cafe Coffee Day - Vidhyadhar nagar :" + String.valueOf(dis6)+ "Km");

        double dis7=distance(lastLocation.getLatitude(), lastLocation.getLongitude(), lat_khandelwal, longt_khandelwal);
        Log.e(TAG, "distance from Khandelwal Pavitra Bhojnalaya :" + String.valueOf(dis7)+ "Km");




        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            address = geocoder.getFromLocation(lastLocation.getLatitude(), lastLocation.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address1 = address.get(0).getAddressLine(0);
        String city = address.get(0).getLocality();
        String state = address.get(0).getAdminArea();
        String country = address.get(0).getCountryName();
        String postalCode = address.get(0).getPostalCode();
        String knownName = address.get(0).getFeatureName();

        Log.e("My Location" , " :" +address1);
        Log.e("city" , " :" +city);
        Log.e(" state" , " :" +state);
        Log.e(" country" , " :" +country);
        Log.e(" postal code" , " :" +postalCode);
        Log.e(" knownName" , " :" +knownName);

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("ADDRESS" , Context.MODE_PRIVATE).edit();
        editor.putString("address" , address1);
        editor.commit();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnectionSuspended(int i) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        Log.e("Fragment :" , "Current User onconnected");
        Log.e("OnConnected  :" , "Google");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }






}








