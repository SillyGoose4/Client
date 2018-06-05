package com.SillyGoose.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class AlbumActivity extends AppCompatActivity {

    private ViewPager pager;
    private List<View> views;
    private ImageButton btn_return;
    MediaPlayer mp=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_album);





        btn_return=(ImageButton)findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent it = new Intent(AlbumActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
        mp=MediaPlayer.create(this, R.raw.btn);
        pager=(ViewPager) findViewById(R.id.viewPager);

        views=new ArrayList<View>();
        LayoutInflater li=getLayoutInflater();
       //views.add(findViewById(R.drawable.airport));
        int pool = R.layout.activity_pool;
        views.add(li.inflate(R.layout.page1, null));
        views.add(li.inflate(R.layout.page2, null));
        views.add(li.inflate(R.layout.page3, null));
        //需要给ViewPager设置适配器
        PagerAdapter adapter=new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0==arg1;
            }
            //有多少个切换页
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return views.size();
            }

            //对超出范围的资源进行销毁
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                //super.destroyItem(container, position, object);
                container.removeView(views.get(position));
            }
            //对显示的资源进行初始化
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                //return super.instantiateItem(container, position);
                container.addView(views.get(position));
                return views.get(position);
            }

        };
        pager.setAdapter(adapter);

        //给ViewPager添加事件监听
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
               // Toast.makeText(AlbumActivity.this, "您选择了："+arg0+"页面", 0).show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(false);

    }
}




