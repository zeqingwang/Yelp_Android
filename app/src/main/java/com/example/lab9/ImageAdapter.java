package com.example.lab9;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ImageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> imagelist=new ArrayList<>();
    private ArrayList<String> imagetitlelist=new ArrayList<>();


    public ImageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return imagelist.get(position);
    }

    @Override
    public int getCount() {
        return imagelist.size();
    }
    public void addFrame(Fragment fragment, String title){
        imagelist.add(fragment);
        imagetitlelist.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return imagetitlelist.get(position);
    }
}
