package com.example.nagar.onlinetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class studentlogin extends AppCompatActivity {

    public void studentregister(View view)
    {
        Intent intent= new Intent(getApplicationContext(),studentregister.class);
        startActivity(intent);
    }

    public void login(View view)
    {
        Intent intent= new Intent(getApplicationContext(),student.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
    }
}
