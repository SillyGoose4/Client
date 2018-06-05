package com.SillyGoose.Activity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TripActivity extends AppCompatActivity {
    private ImageButton btn_return;
    private ImageButton btn_album;
    MediaPlayer mp = new MediaPlayer();
    private ImageButton btn_xibei;
    private ImageButton btn_dongbei;
    private ImageButton btn_西北;
    private ImageButton btn_东北;
    private ImageButton btn_huabei;
    private ImageButton btn_huanan;
    private ImageButton btn_huahzong;
    private ImageButton btn_huadong;
    private ImageButton btn_xinan;
    private ImageView ticket;
    private Bitmap bitmap;
    private ImageView imgbt;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        mp = MediaPlayer.create(this, R.raw.btn);

        imgbt=(ImageView)findViewById(R.id.btn_dongbei);
        /*imgbt.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));
        imgbt.layout(0,0,imgbt.getMeasuredWidth(),imgbt.getMeasuredHeight());
        imgbt.buildDrawingCache();
        imgbt.setDrawingCacheEnabled(true);
        bitmap=Bitmap.createBitmap(imgbt.getDrawingCache());
        imgbt.setDrawingCacheEnabled(false);*/

        bitmap=((BitmapDrawable)(((ImageView)imgbt).getBackground())).getBitmap();//得到ImageButton的图片

        imgbt.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(bitmap.getPixel((int)(event.getX()),((int)event.getY()))==0){//判断点击处像素的颜色是否为0，0表示没 //内容
                    System.out.println("点击区没图像 "+
                            bitmap.getPixel((int)(event.getX()),((int)event.getY())));

                }else{
                    System.out.println("点击区有图像 "+
                            bitmap.getPixel((int)(event.getX()),((int)event.getY())));
                }
                return false;
            }
        });


        //返回按钮
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent it = new Intent(TripActivity.this, MainActivity.class);
                startActivity(it);
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


        //去西北
        btn_xibei = (ImageButton) findViewById(R.id.btn_xibei);
        //bitmap=(ImageView).btn_xibei.getDrawable();//得到ImageButton的图片Im
        /*btn_xibei.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {



               }
            });
        }*/
    }

    private void chenshowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TripActivity.this);
        builder.setTitle("选择高铁类型");
        builder.setSingleChoiceItems(new String[]{"有钱zuo", "有一点钱zuo", "有钱zuo"}, 0, new DialogInterface.OnClickListener() {

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
        builder.setView(ticket);
        // getResources().getDrawable(R.drawable.t_0071);
        //2 添加确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mp.start();
                Toast.makeText(TripActivity.this, "小鹅踏上了去大西北的旅程啦", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        //使用模态，也就是，不对上面的三个按钮操作就不能继续其它操作。（本来：默认是可以其它操作的！！！）
        builder.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(true);

    }
}

