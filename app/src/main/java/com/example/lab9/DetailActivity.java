package com.example.lab9;

import static com.example.lab9.MainActivity.EXTRA_ID;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.widget.Button;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab9.databinding.FragmentDetailBinding;
import com.example.lab9.databinding.FragmentImage1Binding;
import com.example.lab9.databinding.FragmentImage2Binding;
import com.example.lab9.databinding.FragmentImage3Binding;
import com.example.lab9.databinding.FragmentReviewBinding;
import com.google.android.material.tabs.TabLayout;
import com.example.lab9.CustomViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TabLayout detailtab;
    private CustomViewPager detailviewpager;
    String name;
    RequestQueue namerequestqueue;
    ImageView img1,img2,img3;
    String[] img;
    FragmentImage1Binding binding1;
    FragmentImage2Binding binding2;
    FragmentImage3Binding binding3;
    LatLng latLng;
    GoogleMap googleMap;




//    private TabLayout imagetab;
//    private ViewPager imageviewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent intent=getIntent();
        String id=intent.getStringExtra(EXTRA_ID);
//        TextView businessId=findViewById(R.id.buinessId);
//        businessId.setText("id:"+id);
        Button detailreturn=findViewById(R.id.detailreturn);
        detailreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        detailtab=findViewById(R.id.detailtab);
        detailviewpager=findViewById((R.id.detailviewpager));
        detailviewpager.setPagingEnabled(false);

        detailtab.setupWithViewPager(detailviewpager);
        DetailAdapter detailAdapter=new DetailAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        detailAdapter.addFragment(new DetailFragment(id),"BUSINESS DETAILS");
        detailAdapter.addFragment(new MymapFragment(),"MAP LOCATION");
        detailAdapter.addFragment(new ReviewFragment(id),"REVIEWS");
        detailviewpager.setAdapter(detailAdapter);
        String detail_url="https://wzqlab8backend.wl.r.appspot.com/detail?";
        detail_url+="id="+id;
        name(detail_url);




//        review(review_url);









    }
    private Boolean name(String request_url){
        Log.i("abc","detail");
        namerequestqueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
//                    JSONArray business=response.getJSONArray("businesses");
//                    Log.i("result",business.getJSONObject(0).toString());
                    JSONObject detail=response;
                    name=response.getString("name");
                    TextView detailname=findViewById(R.id.detailname);

                    detailname.setText(name);

                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        namerequestqueue.add(jsonObjectRequest);



        return true;
    }


}