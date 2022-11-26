package com.example.lab9;

import static com.example.lab9.MainActivity.EXTRA_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        String id=intent.getStringExtra(EXTRA_ID);
        TextView businessId=findViewById(R.id.buinessId);
        businessId.setText("id:"+id);
    }
}