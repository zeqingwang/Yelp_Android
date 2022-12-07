package com.example.lab9;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.text.Spanned;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Spinner;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.*;

import com.android.volley.Request;
import com.google.android.material.textfield.TextInputEditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchAdapter.OnItemClickListener {
    public static final String EXTRA_ID = "id";
    TextInputEditText keywordinput,locationinput,distanceinput;
    TextView categorytitle;
    AppCompatSpinner categoryspinner;
    RequestQueue iprequestqueue,searchrequestqueue,autorequestqueue;
    String longitude,latitude;

    RecyclerView recyclerView;
    List<Buiness> businessList;
    SearchAdapter adapter;


    AutoCompleteTextView autoText;
    ArrayList<String> list;
    ArrayAdapter<String> arrayAdapter;
    TextView nosearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nosearch=findViewById(R.id.nosearch);
        findViewById(R.id.nosearch).setVisibility(View.INVISIBLE);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

//      set the calender button to reservation page
        Button button1=(Button)findViewById(R.id.buttontoreserve);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ReserveActivity.class);
                startActivity(intent);
            }
        });
        String ipurl="https://ipinfo.io/?token=0466c2dd1050ca";
        ipaddress(ipurl);

//        set  keywordinput
        autoText=findViewById(R.id.autokeywordinput);
        autoText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str=charSequence.toString();
                String auto_url="https://wzqlab8backend.wl.r.appspot.com/autocomplete?";
                auto_url += "keyword=" + str;
                Log.i("auto_url",auto_url);
                auto(auto_url);


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        SpannableString keywordstr = new SpannableString("KewWord *");
        keywordstr.setSpan(new ForegroundColorSpan(Color.RED), 8,     keywordstr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        autoText.setHint(keywordstr);

//      set the distanceinput
        distanceinput=(TextInputEditText)findViewById(R.id.distanceinput);

//        set the span of categorytitle
        categorytitle=(TextView) findViewById(R.id.categorytitle);
        SpannableString categorytitlestr = new SpannableString("Category *");
        categorytitlestr.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0,     8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        categorytitlestr.setSpan(new ForegroundColorSpan(Color.RED), 9,     categorytitlestr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        categorytitle.setText(categorytitlestr);

//        set the span of categoryspiner
        categoryspinner=(AppCompatSpinner) findViewById(R.id.categoryspinner);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.categoryarray));
        categoryspinner.setAdapter(adapter1);

//        set the span of location
        locationinput=(TextInputEditText)findViewById(R.id.locationinput);
        SpannableString locationstr = new SpannableString("Location *");
        locationstr.setSpan(new ForegroundColorSpan(Color.RED), 9,     locationstr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        locationinput.setHint(locationstr);
//set the visibility of location by checkbox
        CheckBox check=(CheckBox)findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check.isChecked()){

                    locationinput.setVisibility(View.INVISIBLE);
                }else{
                    locationinput.setVisibility(View.VISIBLE);
                }
            }
        });

//set the submit button
        Button submitbutton=(Button)findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Boolean inputlegal=true;
                String keyword=autoText.getEditableText().toString();
                if (keyword.isEmpty()){
                    autoText.setError("This field is required");
                    inputlegal=false;
                }
                String distance=distanceinput.getEditableText().toString();
                if (keyword.isEmpty()){
                    distanceinput.setError("This field is required");
                    inputlegal=false;
                }
                String location=locationinput.getEditableText().toString();
                if((!check.isChecked())&&location.isEmpty()){
                    locationinput.setError("This field is required");
                    inputlegal=false;
                }
                String category=categoryspinner.getSelectedItem().toString();
                Log.i("category",category);
                if(inputlegal==false){
                    return;
                }
                Boolean checked=check.isChecked();
                String request_url="https://wzqlab8backend.wl.r.appspot.com/searchbusiness?";
                request_url+="checked="+checked;
                request_url+="&keyword="+keyword;
                request_url+="&distance="+distance;
                request_url+="&category="+category;
                request_url+="&location="+location;
                request_url+="&latitude="+latitude;
                request_url+="&longitude="+longitude;
                Log.i("url",request_url);
                search(request_url);

            }
        });


        Button clearbutton=(Button)findViewById(R.id.clearbutton);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoText.setText("");
                distanceinput.setText("");
                locationinput.setText("");
                categoryspinner.setAdapter(adapter1);
                findViewById(R.id.nosearch).setVisibility(View.INVISIBLE);
                findViewById(R.id.searchrecycler).setVisibility(View.INVISIBLE);


            }
        });



    }
    private boolean ipaddress(String ipurl){
        Log.i("abc","ipaddress");
        iprequestqueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ipurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.i("wzq",response.get("loc").toString());
                    String iplocation=response.get("loc").toString();
                    latitude=iplocation.substring(0,iplocation.indexOf(','));
                    longitude=iplocation.substring(iplocation.indexOf(',')+1,iplocation.length());
                    Log.i("lon",longitude);
                    Log.i("lat",latitude);

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
    private Boolean search(String request_url){
        Log.i("abc","ipaddress");
        searchrequestqueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray business=response.getJSONArray("businesses");
                    int businesslength= business.length();
                    TextView nosearch=findViewById(R.id.nosearch);
                    if(businesslength==0){
                        nosearch.setVisibility(View.VISIBLE);
                        findViewById(R.id.searchrecycler).setVisibility(View.INVISIBLE);
                    }else{
                        nosearch.setVisibility(View.INVISIBLE);
                        findViewById(R.id.searchrecycler).setVisibility(View.VISIBLE);
                    }
                    Log.i("result",business.getJSONObject(0).toString());
                    recyclerView=findViewById(R.id.searchrecycler);
                    businessList=new ArrayList<>();
                    for(int i=0;i<10;i++){
                        Buiness businessline=new Buiness();
                        businessline.setIndex(String.valueOf(i+1));
                        businessline.setImageurl(business.getJSONObject(i).getString("image_url"));
                        businessline.setDistance(String.valueOf((int)(business.getJSONObject(i).getDouble("distance")/1609)));
                        businessline.setName(business.getJSONObject(i).getString("name"));
                        businessline.setRate(String.valueOf(business.getJSONObject(i).getDouble("rating")));
                        businessline.setId(business.getJSONObject(i).getString("id"));
                        businessList.add(businessline);
                        //Log.i("list",businessline.toString());


                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter=new SearchAdapter(getApplicationContext(),businessList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(MainActivity.this);





                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        searchrequestqueue.add(jsonObjectRequest);



        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Buiness clickedItemBusiness = businessList.get(position);
        detailIntent.putExtra(EXTRA_ID, clickedItemBusiness.getId());
        startActivity(detailIntent);

    }
    private boolean auto(String url){
        autorequestqueue = Volley.newRequestQueue(getApplicationContext());
        Log.i("auto","0000");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    Log.i("auto","11111");
                    list=new ArrayList<String>();
                    for(int i=0;i<response.getJSONArray("categories").length();i++){
                        list.add(response.getJSONArray("categories").getJSONObject(i).getString("title"));


                    }
                    for(int i=0;i<response.getJSONArray("terms").length();i++){
                        list.add(response.getJSONArray("terms").getJSONObject(i).getString("text"));

                    }
//                    String [] array=list.toArray();
                    String[] array = new String[list.size()];
                    for(int i = 0; i < list.size(); i++) {
                        array[i] = list.get(i);
                    }
                    arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,array);

                    autoText.setAdapter(arrayAdapter);

//                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    adapter=new SearchAdapter(getApplicationContext(),businessList);
//                    recyclerView.setAdapter(adapter);
//                    adapter.setOnItemClickListener(MainActivity.this);





                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        autorequestqueue.add(jsonObjectRequest);



        return true;

    }

}