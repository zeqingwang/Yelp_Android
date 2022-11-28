package com.example.lab9;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;


public class ImagePagerAdapter extends FragmentPagerAdapter {
   private List<Fragment> fragmentList;
    public ImagePagerAdapter( FragmentManager fm) {
        super(fm);

    }


    public ImagePagerAdapter( FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        return fragmentList.get(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return fragmentList.size();
//    }
}
