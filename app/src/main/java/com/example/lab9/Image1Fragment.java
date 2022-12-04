package com.example.lab9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab9.databinding.FragmentDetailBinding;
import com.example.lab9.databinding.FragmentImage1Binding;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import 		androidx.fragment.app.FragmentPagerAdapter;


public class Image1Fragment extends Fragment {
    String url;
    FragmentImage1Binding binding;
//    public Image1Fragment(String url){
//        this.url=url;
//    }
public static Image1Fragment newInstance(String url) {
    Bundle args = new Bundle();
    args.putString("url", url);
    Image1Fragment f = new Image1Fragment();
    f.setArguments(args);
    Log.i("11111",url);

    return f;
}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentImage1Binding.inflate(getLayoutInflater());
        Log.i("url1",getArguments().getString("url"));
        Picasso.get().load(getArguments().getString("url")).into(binding.img1);


        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_image1, container, false);

    }
}