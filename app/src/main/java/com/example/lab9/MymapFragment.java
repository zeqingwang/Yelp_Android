package com.example.lab9;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MymapFragment extends Fragment {
    RequestQueue iprequestqueue;
    String id;
    public MymapFragment(String id){
        this.id=id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Initialize view
        View view=inflater.inflate(R.layout.fragment_mymap, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                String request_url="https://wzqlab8backend.wl.r.appspot.com/detail?";
                request_url+="id="+id;
                marker(request_url,googleMap);



            }
        });

        return view;
    }
    private Boolean marker(String request_url,GoogleMap googleMap){
        Log.i("abc","detail");
        iprequestqueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{

                    Double latitude=response.getJSONObject("coordinates").getDouble("latitude");
                    Double longitude=response.getJSONObject("coordinates").getDouble("longitude");
                    LatLng latLng=new LatLng(latitude,longitude);
                    MarkerOptions markerOptions=new MarkerOptions();
                    markerOptions.position(latLng);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));
                    googleMap.addMarker(markerOptions);


                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        iprequestqueue.add(jsonObjectRequest);



        return true;
    }
}