package com.example.newsviews.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.newsviews.R;

public class NumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        String query = getIntent().getStringExtra("query");
        Toast.makeText(getApplicationContext()," "+query,Toast.LENGTH_SHORT).show();
    }
}
