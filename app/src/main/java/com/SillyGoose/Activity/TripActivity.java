package com.SillyGoose.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.SillyGoose.Model.Status;

public class TripActivity extends AppCompatActivity {
    private ImageButton btn_return;
    private ImageButton btn_album;
    MediaPlayer mp = new MediaPlayer();
    private Bitmap bitmap;
    private ImageView btn_centralchina;
    private ImageView btn_northchina;
    private ImageView btn_eastchina;
    private ImageView btn_southchina;
    private ImageView btn_southwest;
    private ImageView btn_northwest;
    private ImageView btn_northeast;

    private int tGood=0;
    private int tMiddle=0;
    private int tLow=0;
    int gooseEny=0;
    private TextView tgooseENY;

    //  价位表
    private static int[][] PRICE = {{1,1,1},{1,1,1},{1,1,1}};

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        mp = MediaPlayer.create(this, R.raw.btn);
        tgooseENY=(TextView)findViewById(R.id.tgooseEny);
        tgooseENY.setText(""+Status.getGoose().getGooseEny());
        /*  添加地图触摸事件    东北*/
        btn_northeast = (ImageView) findViewById(R.id.btn_northeast);
        bitmap = ((BitmapDrawable) (((ImageView) btn_northeast).getBackground())).getBitmap();//得到ImageButton的图片
        btn_northeast.setOnTouchListener(touchListener);
        /*  添加地图触摸事件    华中*/
        btn_centralchina = (ImageView) findViewById(R.id.btn_centralchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_centralchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_centralchina.setOnTouchListener(touchListener);
          /*  添加地图触摸事件    华北*/
        btn_northchina = (ImageView) findViewById(R.id.btn_northchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_northchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_northchina.setOnTouchListener(touchListener);
          /*  添加地图触摸事件    华东*/
        btn_eastchina = (ImageView) findViewById(R.id.btn_eastchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_eastchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_eastchina.setOnTouchListener(touchListener);
          /*  添加地图触摸事件    华南*/
        btn_southchina = (ImageView) findViewById(R.id.btn_southchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_southchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_southchina.setOnTouchListener(touchListener);
          /*  添加地图触摸事件    西南*/
        btn_southwest = (ImageView) findViewById(R.id.btn_southwest);
        bitmap = ((BitmapDrawable) (((ImageView) btn_southwest).getBackground())).getBitmap();//得到ImageButton的图片
        btn_southwest.setOnTouchListener(touchListener);
          /*  添加地图触摸事件    西北*/
        btn_northwest = (ImageView) findViewById(R.id. btn_northwest);
        bitmap = ((BitmapDrawable) (((ImageView)  btn_northwest).getBackground())).getBitmap();//得到ImageButton的图片
        btn_northwest.setOnTouchListener(touchListener);
        //返回按钮
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                finish();
            }
        });
        //相册按钮
        btn_album = (ImageButton) findViewById(R.id.btn_album);
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent album = new Intent(TripActivity.this, AlbumActivity.class);
                startActivity(album);
            }
        });
    }
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            int eventaction = event.getAction();
            String city="";
            switch (eventaction) {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("OnSingleTapUp", "Success");
                    if (bitmap.getPixel((int) (event.getX()), ((int) event.getY())) == 0) {//判断点击处像素的颜色是否为0，0表示没 //内容
                        //System.out.println("点击区没图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                    } else {
                        if(view.getId() == R.id.btn_centralchina){
                            city="华中地区";
                            tGood=500;
                            tMiddle=450;
                            tLow=400;
                        }else if(view.getId() == R.id.btn_northchina){
                            city="华北地区";
                            tGood=600;
                            tMiddle=550;
                            tLow=500;
                        }else if(view.getId() == R.id.btn_eastchina){
                            city="华东地区";
                            tGood=400;
                            tMiddle=350;
                            tLow=300;
                        }else if(view.getId() == R.id.btn_southchina){
                            city="华南地区";
                            tGood=600;
                            tMiddle=550;
                            tLow=500;
                        }else if(view.getId() == R.id.btn_southwest){
                            city="西南地区";
                            tGood=700;
                            tMiddle=650;
                            tLow=600;
                        }else if(view.getId() == R.id.btn_northwest){
                            city="西北地区";
                            tGood=900;
                            tMiddle=850;
                            tLow=800;
                        }else if(view.getId() == R.id.btn_northeast){
                            city="东北地区";
                            tGood=700;
                            tMiddle=650;
                            tLow=600;
                        }
                        //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                        chenshowDialog(city,tGood,tLow,tMiddle);
                        Log.d("trip", "onTouch:123 ");

                    }
                    break;
            }
            Log.d("OnTouch", "Success");

            return false;
        }
    };

    private void chenshowDialog(String city, final int tGood, final int tMiddle, final int tLow ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(TripActivity.this);
        builder.setTitle("小鹅要去"+city+"旅行啦,选择交通工具类型吧");
        builder.setSingleChoiceItems(new String[]{"有钱zuo: "+tGood, "有一点钱zuo: "+tMiddle, "没钱zuo: "+tLow}, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    gooseEny = tGood;
                }else if(which==2){
                    gooseEny = tMiddle;
                }else if(which==3){
                    gooseEny = tLow;
                }


            }
        });
        //2 添加确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mp.start();
                if(gooseEny>Status.getGoose().getGooseEny()){
                     Toast.makeText(TripActivity.this,"小鹅没有足够的金币,请继续收集天气吧!",Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println("delta GooseENY : "+ gooseEny);
                    Status.getGoose().setGooseEny(Status.getGoose().getGooseEny() - gooseEny);
                    Toast.makeText(TripActivity.this,"消耗金币"+gooseEny,Toast.LENGTH_SHORT).show();
                    tgooseENY = (TextView) findViewById(R.id.tgooseEny);
                    tgooseENY.setText("" + Status.getGoose().getGooseEny());
                    ticketshowdailog();
                    dialogInterface.dismiss();

                }


            }
        });
        //3 添加否定按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Toast.makeText(TripActivity.this,"以后再说被点击",Toast.LENGTH_SHORT).show();
                mp.start();
                dialogInterface.dismiss();
            }
        });
        //使用模态，也就是，不对上面的按钮操作就不能继续其它操作。（本来：默认是可以其它操作的！！！）
        builder.setCancelable(false);
        builder.show();
    }

    private void ticketshowdailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TripActivity.this);
        builder.setTitle("购票");
        builder.setMessage("小鹅即将乘坐列车");
        builder.setIcon(R.drawable.t_0061);
        //ticket=(ImageView)findViewById(R.id.t_002) ;
        //builder.setView(ticket);
        // getResources().getDrawable(R.drawable.t_0071);
        //2 添加确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mp.start();
                Toast.makeText(TripActivity.this, "小鹅踏上旅程啦", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();

            }
        });
        //使用模态，也就是，不对上面的三个按钮操作就不能继续其它操作。（本来：默认是可以其它操作的！！！）
        builder.show();

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            //test.setImageBitmap((Bitmap) message.obj);
            return false;
        }
    });


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(false);
        this.finish();
    }
}