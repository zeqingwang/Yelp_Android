package com.example.lab9;

import static com.example.lab9.MainActivity.EXTRA_ID;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.widget.Button;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.example.lab9.CustomViewPager;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TabLayout detailtab;
    private CustomViewPager detailviewpager;

//    private TabLayout imagetab;
//    private ViewPager imageviewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.hide();
//        }
        //getSupportActionBar().setTitle("Hello world App");
        toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent intent=getIntent();
        String id=intent.getStringExtra(EXTRA_ID);
//        TextView businessId=findViewById(R.id.buinessId);
//        businessId.setText("id:"+id);
        Button detailreturn=findViewById(R.id.detailreturn);
        detailreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        detailtab=findViewById(R.id.detailtab);
        detailviewpager=findViewById((R.id.detailviewpager));
        detailviewpager.setPagingEnabled(false);

//        detailtab.addTab(detailtab.newTab().setText("BUSINESS DETAILS"));
//        detailtab.addTab(detailtab.newTab().setText("MAP LOCATION"));
//        detailtab.addTab(detailtab.newTab().setText("REVIEWS"));
//        detailtab.setTabGravity(TabLayout.GRAVITY_FILL);
//        detailtab.setupWithViewPager(detailviewpager);
//
//        DetailAdapter detailAdapter=new DetailAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        detailviewpager.setAdapter(detailAdapter);
//
//        detailviewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(detailtab));
//
//        detailtab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                detailviewpager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        detailtab.setupWithViewPager(detailviewpager);
        DetailAdapter detailAdapter=new DetailAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        detailAdapter.addFragment(new DetailFragment(),"BUSINESS DETAILS");
        detailAdapter.addFragment(new MapFragment(),"MAP LOCATION");
        detailAdapter.addFragment(new ReviewFragment(),"REVIEWS");
        detailviewpager.setAdapter(detailAdapter);






    }
}