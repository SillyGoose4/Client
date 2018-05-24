package com.SillyGoose.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.SillyGoose.Model.OkHttpUnits;

import connect.database.test.com.clents.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn_pool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_pool=(ImageButton)findViewById(R.id.btn_pond);
        btn_pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainit=new Intent(MainActivity.this,PoolActivity.class);
                startActivity(mainit);

            }
        });

        OkHttpUnits client=OkHttpUnits.getInstance();
        startActivity(new Intent(MainActivity.this,SignInActivity.class));

    }

}
