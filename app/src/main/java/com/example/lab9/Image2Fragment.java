package com.example.lab9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab9.databinding.FragmentImage1Binding;
import com.example.lab9.databinding.FragmentImage2Binding;
import com.squareup.picasso.Picasso;


public class Image2Fragment extends Fragment {
    String url;
    FragmentImage2Binding binding;
//    public Image2Fragment(String url){
//        this.url=url;
//    }
public static Image2Fragment newInstance(String url) {
    Bundle args = new Bundle();
    args.putString("url", url);
    Image2Fragment f = new Image2Fragment();
    f.setArguments(args);
    return f;
}




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentImage2Binding.inflate(getLayoutInflater());
        Picasso.get().load(getArguments().getString("url")).into(binding.img2);


        return binding.getRoot();
    }
}