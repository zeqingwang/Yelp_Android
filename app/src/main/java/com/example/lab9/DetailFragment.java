package com.example.lab9;

import static android.content.Intent.getIntent;
import static com.example.lab9.MainActivity.EXTRA_ID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab9.databinding.FragmentDetailBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends Fragment {


    private ViewPager imageviewpager;
    private ImagePagerAdapter imagePagerAdapter;
    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Fragment> list=new ArrayList<>();
        list.add(new Image1Fragment());
        list.add(new Image2Fragment());
//        list.add(new Image3Fragment());

        binding=FragmentDetailBinding.inflate(getLayoutInflater());
        imagePagerAdapter=new ImagePagerAdapter(getChildFragmentManager(),list);
        binding.imageviewpager.setAdapter(imagePagerAdapter);
//        Intent intent=getIntent();
//        String[] img=new String[3];
//        img=intent.getStringArrayExtra("img");


        return binding.getRoot();

        //return inflater.inflate(R.layout.fragment_detail, container, false);

    }

}