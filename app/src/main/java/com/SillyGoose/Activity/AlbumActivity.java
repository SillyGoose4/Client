
package com.SillyGoose.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import connect.database.test.com.clents.R;

/**
 * test
 */
public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(true);

    }
}
