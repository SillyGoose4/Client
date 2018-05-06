package com.SillyGoose.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.SillyGoose.Model.OkHttpUnits;

import connect.database.test.com.clents.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpUnits client=OkHttpUnits.getInstance();
        startActivity(new Intent(MainActivity.this,SignInActivity.class));
    }
}
