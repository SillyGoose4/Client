package com.SillyGoose.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class AlbumActivity extends AppCompatActivity {


    private ImageButton btn_return;
    MediaPlayer mp=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        btn_return=(ImageButton)findViewById(R.id.btn_return);
        mp=MediaPlayer.create(this, R.raw.btn);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent it = new Intent(AlbumActivity.this, MainActivity.class);
                startActivity(it);
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(true);

    }
}
