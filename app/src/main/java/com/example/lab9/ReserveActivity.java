package com.example.lab9;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReserveActivity extends AppCompatActivity {
    RecyclerView reserveView;
    List<Reserve> reserveList;
    ReserveAdapter reserveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        Button reservereturn=findViewById(R.id.reservereturn);
        reservereturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SharedPreferences sharedPreferences= getSharedPreferences("Reserve",0);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        int length=allEntries.size();
        Toast.makeText(this,String.valueOf(length),Toast.LENGTH_SHORT).show();
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        String value=name+","+submitedate+","+submitetime+","+submiteemail;
//        String key=name;
//        editor.putString(key,value);
//        editor.apply();
        reserveView=findViewById(R.id.reserverecycler);
        reserveList=new ArrayList<>();
        int linecount=1;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()){
            String[]data=entry.getValue().toString().split(",");
            Reserve reserveline=new Reserve();
            reserveline.setIndex(String.valueOf(linecount));
            reserveline.setName(data[0]);
            reserveline.setDate(data[1]);
            reserveline.setTime(data[2]);
            reserveline.setEmail(data[3]);
            reserveList.add(reserveline);
            linecount++;
            //Log.i("list",businessline.toString());


        }
        reserveView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        reserveAdapter=new ReserveAdapter(getApplicationContext(),reserveList);
        reserveView.setAdapter(reserveAdapter);
//        reserveAdapter.setOnItemClickListener(getActivity());


    }
}