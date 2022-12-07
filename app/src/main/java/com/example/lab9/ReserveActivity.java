package com.example.lab9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class ReserveActivity extends AppCompatActivity {
    RecyclerView reserveView;
    List<Reserve> reserveList;
    ReserveAdapter reserveAdapter;
    private RelativeLayout reservelayout;
    SharedPreferences sharedPreferences;

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
        reservelayout=findViewById(R.id.reservelayout);
        sharedPreferences= getSharedPreferences("Reserve",0);

        Map<String, ?> allEntries = sharedPreferences.getAll();
        int length=allEntries.size();
        if(length==0){
            findViewById(R.id.nobook).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.nobook).setVisibility(View.INVISIBLE);
        }

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
        swipeToDelete();
//        sharedPreferences= getSharedPreferences("Reserve",0);
//        allEntries = sharedPreferences.getAll();
//        length=allEntries.size();
//        if(length==0){
//            findViewById(R.id.nobook).setVisibility(View.VISIBLE);
//        }
//        reserveAdapter.setOnItemClickListener(getActivity());


    }
    private void swipeToDelete() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                Reserve clickedItemReserve = reserveList.get(position);
                reserveList.remove(position);
                String removedKey=clickedItemReserve.getName();
                SharedPreferences sharedPreferences=getSharedPreferences("Reserve",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                reserveView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                reserveAdapter=new ReserveAdapter(getApplicationContext(),reserveList);
                reserveView.setAdapter(reserveAdapter);
                if(reserveList.size()==0){
                    findViewById(R.id.nobook).setVisibility(View.VISIBLE);

                }
                editor.remove(removedKey).commit();
                Snackbar snackbar = Snackbar.make(reservelayout, "Removing Exiting Reservation.", Snackbar.LENGTH_LONG);
                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                View snackView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.snackbar_layout, null);
                snackbar.getView().setPadding(0,0,0,15);
                TextView textViewTop = (TextView) snackView.findViewById(R.id.snackbar_text);
                textViewTop.setText("Removing Exiting Reservation.");
                layout.addView(snackView, 0);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(reserveView);
        SharedPreferences sharedPreferences= getSharedPreferences("Reserve",0);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        int length=allEntries.size();
        if(length==0){
            findViewById(R.id.nobook).setVisibility(View.VISIBLE);
        }
    }
}