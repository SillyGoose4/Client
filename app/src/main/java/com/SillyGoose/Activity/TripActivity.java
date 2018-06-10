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
import android.widget.Toast;

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
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        mp = MediaPlayer.create(this, R.raw.btn);

        /*  添加地图触摸事件    东北*/
        btn_northeast = (ImageView) findViewById(R.id.btn_northeast);
        bitmap = ((BitmapDrawable) (((ImageView) btn_northeast).getBackground())).getBitmap();//得到ImageButton的图片
        btn_northeast.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
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
                            //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                            chenshowDialog();
                            Log.d("trip", "onTouch:123 ");
                        }
                        break;
                }
                Log.d("OnTouch", "Success");
                return false;

            }
        });
        /*  添加地图触摸事件    华中*/
        btn_centralchina = (ImageView) findViewById(R.id.btn_centralchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_centralchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_centralchina.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
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
                            //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                            chenshowDialog();
                            Log.d("trip", "onTouch:123 ");
                        }
                        break;
                }
                Log.d("OnTouch", "Success");
                return false;

            }
        });
          /*  添加地图触摸事件    华北*/
        btn_northchina = (ImageView) findViewById(R.id.btn_northchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_northchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_northchina.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
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
                            //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                            chenshowDialog();
                            Log.d("trip", "onTouch:123 ");
                        }
                        break;
                }
                Log.d("OnTouch", "Success");
                return false;

            }
        });
          /*  添加地图触摸事件    华东*/
        btn_eastchina = (ImageView) findViewById(R.id.btn_eastchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_eastchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_eastchina.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
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
                            //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                            chenshowDialog();
                            Log.d("trip", "onTouch:123 ");
                        }
                        break;
                }
                Log.d("OnTouch", "Success");
                return false;

            }
        });
          /*  添加地图触摸事件    华南*/
        btn_southchina = (ImageView) findViewById(R.id.btn_southchina);
        bitmap = ((BitmapDrawable) (((ImageView) btn_southchina).getBackground())).getBitmap();//得到ImageButton的图片
        btn_southchina.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
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
                            //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                            chenshowDialog();
                            Log.d("trip", "onTouch:123 ");
                        }
                        break;
                }
                Log.d("OnTouch", "Success");
                return false;

            }
        });
          /*  添加地图触摸事件    西南*/
        btn_southwest = (ImageView) findViewById(R.id.btn_southwest);
        bitmap = ((BitmapDrawable) (((ImageView) btn_southwest).getBackground())).getBitmap();//得到ImageButton的图片
        btn_southwest.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
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
                            //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                            chenshowDialog();
                            Log.d("trip", "onTouch:123 ");
                        }
                        break;
                }
                Log.d("OnTouch", "Success");
                return false;

            }
        });
          /*  添加地图触摸事件    西北*/
        btn_northwest = (ImageView) findViewById(R.id. btn_northwest);
        bitmap = ((BitmapDrawable) (((ImageView)  btn_northwest).getBackground())).getBitmap();//得到ImageButton的图片
        btn_northwest.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
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
                            //System.out.println("点击区有图像 "+bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                            chenshowDialog();
                            Log.d("trip", "onTouch:123 ");
                        }
                        break;
                }
                Log.d("OnTouch", "Success");
                return false;

            }
        });

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
    private void chenshowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TripActivity.this);
        builder.setTitle("选择类型");
        builder.setSingleChoiceItems(new String[]{"有钱zuo", "有一点钱zuo", "没钱zuo"}, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //2 添加确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //**************还没有把高铁参数传给
                mp.start();
                ticketshowdailog();
                dialogInterface.dismiss();
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
        builder.setMessage("小鹅即将乘坐***zuo列车");
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
                        /* for test
                        try {
                            Bitmap bitmap = OkHttpUnits.getPic();
                            Message message = handler.obtainMessage();
                            message.obj = bitmap;
                            message.sendToTarget();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        */
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

    }
}