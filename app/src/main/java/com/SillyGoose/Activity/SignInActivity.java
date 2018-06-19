package com.SillyGoose.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.SillyGoose.Model.CollectTime;
import com.SillyGoose.Model.Status;
import com.SillyGoose.Model.User;
import com.SillyGoose.Utils.MessageBox;
import com.SillyGoose.Utils.OkHttpUnits;
import com.baidu.location.LocationClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 使用SharedPreferences自动登录以及记住密码
 * writer : jiaruo wang
 * last change in 5/3/2018
 * wangjiaruo22@hotmail.com
 */
public class SignInActivity extends AppCompatActivity {

    private Button btn_SignIn;
    private Button btn_SignUp;
    private TextView text_Phone;
    private TextView text_Passwd;
    private CheckBox checkBox_remember;
    private Thread thread;
    private LocationClient location = null;

    long mExitTime;
    private final String TAG = "SignInActivity Called:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        /* initizalize */
        text_Passwd=(TextView)findViewById(R.id.SI_edit_Passwd);
        text_Phone=(TextView)findViewById(R.id.SI_edit_Phone);
        btn_SignIn=(Button)findViewById(R.id.btn_SignIn);
        btn_SignUp=(Button)findViewById(R.id.btn_SignUp);
        checkBox_remember=(CheckBox)findViewById(R.id.SI_cb_AutoSignIn);

        /* add Event Listener */
        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();

            }

        });

        btn_SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(it);
            }
        });

        // Return to MainActivity after finish


    }

    @Override
    public void onBackPressed() {
        /* there is Something Wrong in this Method */
        long now = System.currentTimeMillis();

        if((now - mExitTime) > 2000) {
            Toast.makeText(getApplicationContext(),"再按返回键退出！",Toast.LENGTH_LONG).show();
            mExitTime = now;   //这里赋值最关键，别忘记
        }
        else{
            stopService(new Intent(getApplicationContext(),BgmService.class));
            SignInActivity.this.finish();   //关闭本活动页面
            MainActivity.Instance.finish();
            Status.setIsSignIn(false);
            //System.exit(0);
        }
    }


    /**
     * 登录请求处理
     * 由于子线程不能更新UI，所以新增Handler类通过传送Message更新UI
     */
    private void SignIn() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run On SignIn: ======================================================================");
                MessageBox messageBox;
                JSONObject data = new JSONObject();
                JSONObject result ;
                try {
                    data.put("Value", "SIGNIN");
                    data.put("Phone", text_Phone.getText().toString());
                    data.put("Passwd", text_Passwd.getText().toString());
                    //messageBox = OkHttpUnits.post(OkHttpUnits.setAndGetUrl("/post"), data);
                    result = OkHttpUnits.postForGetJSON(OkHttpUnits.setAndGetUrl("/post"),data);
                    messageBox =MessageBox.valueOf(result.getString("Result"));
                    if(messageBox == MessageBox.SI_SUCCESS){
                        User recive=new User();
                        recive.setUserId(result.getJSONObject("Message").getJSONObject("User").getInt("userId"));
                        recive.setUserName(result.getJSONObject("Message").getJSONObject("User").getString("userName"));
                        recive.setUserPhone(result.getJSONObject("Message").getJSONObject("User").getString("userPhone"));
                        recive.setUserPasswd(result.getJSONObject("Message").getJSONObject("User").getString("userPasswd"));
                        recive.setLastSignIn(result.getJSONObject("Message").getJSONObject("User").getString("lastSignIn"));
                        //Goose goose = new Goose(result.getJSONObject("Message").getJSONObject("Goose"));
                        CollectTime collectTime = new CollectTime(result.getJSONObject("Message").getJSONObject("CollectTime"));
                        JSONArray alb = result.getJSONObject("Message").getJSONArray("Album");
                        //List<Album> albums = alb
                        Status.setCollectTime(collectTime);
                        Status.setGoose(result.getJSONObject("Message").getJSONObject("Goose").getInt("gooseEny"));
                        Status.setUser(recive);
                    }
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    msg.obj = messageBox;
                    msg.sendToTarget();
                } catch (JSONException je) {
                    je.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        thread.start();

    }

    //@SuppressLint("HandlerLeak")
    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            /*记住密码*/
            if(checkBox_remember.isChecked()){

            }
            if(msg.obj == MessageBox.SI_SUCCESS){
                Toast.makeText(getApplicationContext(), "登录成功",
                        Toast.LENGTH_LONG).show();
                Status.setIsSignIn(true);
                Intent intent = new Intent();
                intent.putExtra("Value","OK");
                setResult(2,intent);
                SignInActivity.this.finish();
            }else if(msg.obj == MessageBox.SI_NOTFIND){
                Toast.makeText(getApplicationContext(), "未找到用户",
                        Toast.LENGTH_LONG).show();
            }else if(msg.obj == MessageBox.SI_PASSWORDWRONG){
                Toast.makeText(getApplicationContext(), "密码错误",
                        Toast.LENGTH_LONG).show();
            }else if(msg.obj == MessageBox.SI_ALREADYSIGNIN){
                Toast.makeText(getApplicationContext(), "该用户已登录",
                        Toast.LENGTH_LONG).show();
            }else if(msg.obj == MessageBox.SYS_ERROR){
                Toast.makeText(getApplicationContext(), "系统错误",
                        Toast.LENGTH_LONG).show();
            }
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //thread.stop();
        Log.d(TAG, "onDestroy: this activity will destroy");
    }
}

