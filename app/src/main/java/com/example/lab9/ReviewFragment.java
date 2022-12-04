package com.example.lab9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.example.lab9.databinding.FragmentReviewBinding;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


public class ReviewFragment extends Fragment {

   private String id;
   private RequestQueue reviewrequestqueue;
    String name1s,rate1s,review1s,date1s;
    String name2s,rate2s,review2s,date2s;
    String name3s,rate3s,review3s,date3s;
    TextView name1,rate1,review1,date1;
    TextView  name2,rate2,review2,date2;
    TextView  name3,rate3,review3,date3;
    FragmentReviewBinding binding;
   public ReviewFragment(String id){
       this.id=id;
   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentReviewBinding.inflate(getLayoutInflater());
        String review_url="https://wzqlab8backend.wl.r.appspot.com/review?";
        review_url+="id="+id;
       review(review_url);

        return binding.getRoot();
    }
    private Boolean review(String request_url){
        Log.i("abc","detail");
        reviewrequestqueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    int reviewnum=response.getJSONArray("reviews").length();
                    if(reviewnum>=1){
                        name1s=response.getJSONArray("reviews").getJSONObject(0).getJSONObject("user").getString("name");
                        rate1s="Rating :"+ response.getJSONArray("reviews").getJSONObject(0).getInt("rating") +"/5";
                        review1s=response.getJSONArray("reviews").getJSONObject(0).getString("text");
                        String[] datearray=response.getJSONArray("reviews").getJSONObject(0).getString("time_created").split("\\ ");
                        date1s=datearray[0];
                        Log.i("review1",name1s);
                        binding.name1.setText(name1s);
                        binding.rate1.setText(rate1s);
                        binding.review1.setText(review1s);
                        binding.date1.setText(date1s);

                    }
                    if(reviewnum>=2){
                        name2s=response.getJSONArray("reviews").getJSONObject(1).getJSONObject("user").getString("name");
                        rate2s="Rating :"+ response.getJSONArray("reviews").getJSONObject(1).getInt("rating") +"/5";
                        review2s=response.getJSONArray("reviews").getJSONObject(1).getString("text");
                        String[] datearray=response.getJSONArray("reviews").getJSONObject(1).getString("time_created").split("\\ ");
                        date2s=datearray[0];
                        Log.i("review3",name2s);
                        binding.name2.setText(name2s);
                        binding.rate2.setText(rate2s);
                        binding.review2.setText(review2s);
                        binding.date2.setText(date2s);

                    }
                    if(reviewnum>=3){
                        name3s=response.getJSONArray("reviews").getJSONObject(2).getJSONObject("user").getString("name");
                        rate3s="Rating :"+ response.getJSONArray("reviews").getJSONObject(2).getInt("rating") +"/5";
                        review3s=response.getJSONArray("reviews").getJSONObject(2).getString("text");
                        String[] datearray=response.getJSONArray("reviews").getJSONObject(2).getString("time_created").split("\\ ");
                        date3s=datearray[0];
                        Log.i("review3",name3s);
                        binding.name3.setText(name3s);
                        binding.rate3.setText(rate3s);
                        binding.review3.setText(review3s);
                        binding.date3.setText(date3s);

                    }




                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        reviewrequestqueue.add(jsonObjectRequest);



        return true;
    }

}