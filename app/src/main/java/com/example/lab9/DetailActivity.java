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
//import com.google.android.gms.maps.MapFragment;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TabLayout detailtab;
    private CustomViewPager detailviewpager;
    RequestQueue detailrequestqueue;
    String name,address,price,number,category,link,img1url,img2url,img3url;
    Boolean status;
    ImageView img1,img2,img3;
    String[] img;
    FragmentImage1Binding binding1;
    FragmentImage2Binding binding2;
    FragmentImage3Binding binding3;


//    private TabLayout imagetab;
//    private ViewPager imageviewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.hide();
//        }
        //getSupportActionBar().setTitle("Hello world App");
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
        detailAdapter.addFragment(new DetailFragment(),"BUSINESS DETAILS");
        detailAdapter.addFragment(new MapFragment(),"MAP LOCATION");
        detailAdapter.addFragment(new ReviewFragment(),"REVIEWS");
        detailviewpager.setAdapter(detailAdapter);

        String detail_url="https://wzqlab8backend.wl.r.appspot.com/detail?";
        detail_url+="id="+id;
        detail(detail_url);









    }
    private Boolean detail(String request_url){
        Log.i("abc","detail");
        detailrequestqueue = Volley.newRequestQueue(getApplicationContext());

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

                    TextView businessaddress=findViewById(R.id.businessaddress);
                    JSONArray addressarray=response.getJSONObject("location").getJSONArray("display_address");
                    address="";
                    for(int i=0;i< addressarray.length();i++){
                        String s=addressarray.getString(i);
                        address+=s;
                        if(i!=(addressarray.length()-1)){
                            address+=",";
                        }
                    }
                    businessaddress.setText(address);

                    TextView businessprice=findViewById(R.id.businessprice);
                    price=response.getString("price");
                    businessprice.setText(price);

                    TextView businessnumber=findViewById(R.id.businessnumber);
                    number=response.getString("display_phone");
                    Log.i("number",number);
                    businessnumber.setText(number);

                    TextView businessstatus=findViewById(R.id.businessstatus);
                    status=response.getJSONArray("hours").getJSONObject(0).getBoolean("is_open_now");
                    if(status=true){
                        businessstatus.setText("Open Now");
                        businessstatus.setTextColor(Color.GREEN);
                    }else{
                        businessstatus.setText("Closed");
                        businessstatus.setTextColor(Color.RED);
                    }
                    TextView businesscategory=findViewById(R.id.businesscategory);
                    JSONArray categories=response.getJSONArray("categories");
                    category="";
                    for(int i=0;i< categories.length();i++){
                        String s=categories.getJSONObject(i).getString("title");
                        category+=s;
                        if(i!=(categories.length()-1)){
                            category+="|";
                        }
                    }
                    businesscategory.setText(category);
                    TextView businesslink=findViewById(R.id.businesslink);
                    link=response.getString("url");

                    img1url=response.getJSONArray("photos").getString(0);
                    img2url=response.getJSONArray("photos").getString(1);
                    img3url=response.getJSONArray("photos").getString(2);
//                    binding1= FragmentImage1Binding.inflate(getLayoutInflater());
//                    binding2= FragmentImage2Binding.inflate(getLayoutInflater());
//                    binding3= FragmentImage3Binding.inflate(getLayoutInflater());
//                    img1=binding1.img1;
//                    Picasso.get().load(img1url).into(img1);
//                    img2=binding2.img2;
//                    Picasso.get().load(img2url).into(img2);
//                    img3=binding3.img3;
//                    Picasso.get().load(img3url).into(img3);
                    img1=findViewById(R.id.img1);
                    Picasso.get().load(img1url).into(img1);
                    img2=findViewById(R.id.img2);
                    Picasso.get().load(img2url).into(img2);
//                    img3=findViewById(R.id.img3);
//                    Log.i("img3",img3.toString());
//                    Picasso.get().load(img3url).into(img3);
//                    img=new String[3];
//                    img[0]=img1url;
//                    img[1]=img2url;
//                    img[2]=img3url;
//                    Intent imgIntent = new Intent(DetailActivity.this, DetailFragment.class);
//
//                    imgIntent.putExtra("img", img);
//                    startActivity(imgIntent);












//                        businessline.setIndex(String.valueOf(i+1));
//                        businessline.setImageurl(business.getJSONObject(i).getString("image_url"));
//                        businessline.setDistance(String.valueOf((int)(business.getJSONObject(i).getDouble("distance")/1609)));
//                        businessline.setName(business.getJSONObject(i).getString("name"));
//                        businessline.setRate(String.valueOf(business.getJSONObject(i).getDouble("rating")));
//                        businessline.setId(business.getJSONObject(i).getString("id"));
//                        businessList.add(businessline);








//                    String iplocation=response.get("loc").toString();
//                    latitude=iplocation.substring(0,iplocation.indexOf(','));
//                    longitude=iplocation.substring(iplocation.indexOf(',')+1,iplocation.length());
//                    Log.i("lon",longitude);
//                    Log.i("lat",latitude);

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