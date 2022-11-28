package com.example.lab9;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class DetailAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> detailfragmentlist=new ArrayList<>();
    private final ArrayList<String> detailtitlelist=new ArrayList<>();


    public DetailAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return detailfragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return detailfragmentlist.size();
    }
    public void addFragment(Fragment fragment,String title){
        detailfragmentlist.add(fragment);
        detailtitlelist.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return detailtitlelist.get(position);
    }
}
