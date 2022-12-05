package com.example.lab9;

import static android.content.Intent.getIntent;
import static com.example.lab9.MainActivity.EXTRA_ID;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab9.databinding.FragmentDetailBinding;
import com.example.lab9.databinding.FragmentReviewBinding;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends Fragment {


    private ViewPager imageviewpager;
    private ImagePagerAdapter imagePagerAdapter;
    private FragmentDetailBinding binding;
    private String id;
    RequestQueue detailrequestqueue;
    String name,address,price,number,category,link,img1url,img2url,img3url;
    Boolean status;
    Double latitude,longitude;
    public DetailFragment(String id){
        this.id=id;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding=FragmentDetailBinding.inflate(getLayoutInflater());
        String detail_url="https://wzqlab8backend.wl.r.appspot.com/detail?";
        detail_url+="id="+id;
        detail(detail_url);




        return binding.getRoot();

        //return inflater.inflate(R.layout.fragment_detail, container, false);

    }
    private Boolean detail(String request_url){
        Log.i("abc","detail");
        detailrequestqueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
//                    JSONArray business=response.getJSONArray("businesses");
//                    Log.i("result",business.getJSONObject(0).toString());
                    JSONObject detail=response;



                    JSONArray addressarray=response.getJSONObject("location").getJSONArray("display_address");
                    address="";
                    for(int i=0;i< addressarray.length();i++){
                        String s=addressarray.getString(i);
                        address+=s;
                        if(i!=(addressarray.length()-1)){
                            address+=",";
                        }
                    }
                    binding.businessaddress.setText(address);


                    price=response.getString("price");
                    binding.businessprice.setText(price);


                    number=response.getString("display_phone");
                    Log.i("number",number);
                    binding.businessnumber.setText(number);


                    status=response.getJSONArray("hours").getJSONObject(0).getBoolean("is_open_now");
                    if(status=true){
                        binding.businessstatus.setText("Open Now");
                        binding.businessstatus.setTextColor(Color.GREEN);
                    }else{
                        binding.businessstatus.setText("Closed");
                        binding.businessstatus.setTextColor(Color.RED);
                    }

                    JSONArray categories=response.getJSONArray("categories");
                    category="";
                    for(int i=0;i< categories.length();i++){
                        String s=categories.getJSONObject(i).getString("title");
                        category+=s;
                        if(i!=(categories.length()-1)){
                            category+="|";
                        }
                    }
                    binding.businesscategory.setText(category);
//                    TextView businesslink=findViewById(R.id.businesslink);
                    link=response.getString("url");

                    img1url=response.getJSONArray("photos").getString(0);
                    img2url=response.getJSONArray("photos").getString(1);
                    img3url=response.getJSONArray("photos").getString(2);
                    Log.i("01",img1url);
                    List<Fragment> list=new ArrayList<>();
                    list.add(Image1Fragment.newInstance(img1url));
                    list.add(Image2Fragment.newInstance(img2url));
                    list.add(Image3Fragment.newInstance(img3url));
                    imagePagerAdapter=new ImagePagerAdapter(getChildFragmentManager(),list);
                    binding.imageviewpager.setAdapter(imagePagerAdapter);

                    latitude=response.getJSONObject("coordinates").getDouble("latitude");
                    longitude=response.getJSONObject("coordinates").getDouble("longitude");


                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        detailrequestqueue.add(jsonObjectRequest);



        return true;
    }


}