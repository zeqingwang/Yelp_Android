package com.example.lab9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab9.databinding.FragmentImage2Binding;
import com.example.lab9.databinding.FragmentImage3Binding;
import com.squareup.picasso.Picasso;

public class Image3Fragment extends Fragment {
    String url;
    FragmentImage3Binding binding;
//    public Image3Fragment(String url){
//        this.url=url;
//    }
public static Image3Fragment newInstance(String url) {
    Bundle args = new Bundle();
    args.putString("url", url);
    Image3Fragment f = new Image3Fragment();
    f.setArguments(args);
    return f;
}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentImage3Binding.inflate(getLayoutInflater());
        Picasso.get().load(getArguments().getString("url")).into(binding.img3);
        return binding.getRoot();
    }
}