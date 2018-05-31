package com.SillyGoose.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.SillyGoose.Model.OkHttpUnits;
import com.SillyGoose.Utils.MessageBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.SillyGoose.Activity.R;

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
    }

    /**
     * 登录请求处理
     * 由于子线程不能更新UI，所以新增Handler类通过传送Message更新UI
     */
    private void SignIn() {

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("======================================================================");
                MessageBox messageBox;
                JSONObject data = new JSONObject();
                try {
                    data.put("Value", "SIGNIN");
                    data.put("Phone", text_Phone.getText().toString());
                    data.put("Passwd", text_Passwd.getText().toString());
                    messageBox = OkHttpUnits.post("http://192.168.126.131:8080/user/login", data);
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
        myThread.start();

    }

    //@SuppressLint("HandlerLeak")
    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if(checkBox_remember.isChecked()){

            }
            if(msg.obj == MessageBox.SI_SUCCESS){
                Toast.makeText(getApplicationContext(), "登录成功",
                        Toast.LENGTH_LONG).show();
            }else if(msg.obj == MessageBox.SI_NOTFIND){
                Toast.makeText(getApplicationContext(), "未找到用户",
                        Toast.LENGTH_LONG).show();
            }else if(msg.obj == MessageBox.SI_PASSWORDWRONG){
                Toast.makeText(getApplicationContext(), "密码错误",
                        Toast.LENGTH_LONG).show();
            }
            return false;
        }
    });

}
