package com.example.lab9;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab9.databinding.FragmentDetailBinding;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DetailFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    private ViewPager imageviewpager;
    private ImagePagerAdapter imagePagerAdapter;
    private FragmentDetailBinding binding;
    private String id;
    RequestQueue detailrequestqueue;
    String name,address,price,number,category,link,img1url,img2url,img3url;
    Boolean status;
    Double latitude,longitude;
    private TextInputEditText reserveemail,reservedate,reservetime;
    private Button cancel, submit;
    TextView reservename;
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
        binding.reservenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater=getActivity().getLayoutInflater();
                View reserveview=inflater.inflate(R.layout.dialog_layout,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setView(reserveview);
                AlertDialog dialog = builder.create();
                reserveemail=reserveview.findViewById(R.id.reserveemail);
                reservedate=reserveview.findViewById(R.id.reservedate);

                reservedate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayDatepicker();
                    }
                });
                reservetime=reserveview.findViewById(R.id.reservetime);
                reservetime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayTimepicker();

                    }
                });
                submit=reserveview.findViewById(R.id.submitreserve);
                cancel=reserveview.findViewById(R.id.cancelreserve);
                reservename=reserveview.findViewById(R.id.reservename);
                reservename.setText(name);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean legal=true;
                        String submitemail=reserveemail.getText().toString();
                        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(submitemail);
                        if (matcher.matches()==false){
                            Toast.makeText(getContext(),"InValid Email Format",Toast.LENGTH_SHORT).show();
                            legal=false;
                        }
                        String submitedate=reservedate.getText().toString();
                        if((submitedate=="")||(submitedate==" ")||(submitedate==null)){
                            Toast.makeText(getContext(),"Date can't be empty",Toast.LENGTH_SHORT).show();
                            legal=false;
                        }
                        String submitetime=reservetime.getText().toString();
                        if((submitetime=="")||(submitetime==" ")||(submitetime==null)){
                            Toast.makeText(getContext(),"Time should be between 10AM AND 5PM",Toast.LENGTH_SHORT).show();
                            legal=false;

                        }else{
                            String[] timearray = submitetime.split(":");
                            if(timearray[0]!=""){
                                int submithour=Integer.valueOf(timearray[0]);
                                if((submithour<=10)||(submithour>=17)){
                                    Toast.makeText(getContext(),"Time should be between 10AM AND 5PM",Toast.LENGTH_SHORT).show();
                                    legal=false;
                                }
                            }

                        }

                        if (legal==true){
                            Toast.makeText(getContext(),"Reservation Booked",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



                dialog.show();

//                Dialog dialog=new Dialog();
//                dialog.show(
//                        getChildFragmentManager(),"dialog"
//                );

            }
        });




        return binding.getRoot();

        //return inflater.inflate(R.layout.fragment_detail, container, false);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        String datestring=month+"-"+day+"-"+year;
        reservedate.setText(datestring);
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
                    name=response.getString("name");




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
    public void displayDatepicker(){
        Calendar calendar= Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),Calendar.DAY_OF_MONTH);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    public void displayTimepicker(){
        Calendar calendar= Calendar.getInstance();
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String timestr=String.valueOf(hourOfDay)+":"+String.valueOf(minute);
                reservetime.setText(timestr);


            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);


        timePickerDialog.show();
    }


//    @Override
//    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//
//
//    }




}