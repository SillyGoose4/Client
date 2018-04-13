package connect.database.test.com.clents;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaCodec;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.os.Message;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;
import com.mob.tools.RxMob;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 使用 MOB提供的sdk 手机短信验证
 */
public class SignUpActivity extends AppCompatActivity {

    private Button btn_SignUp;
    private Button btn_getCode;
    private TextView phone;
    private TextView name;
    private TextView passwd;
    private TextView verpasswd;
    private TextView verification;
    private CountDownTimer timer;
    public EventHandler eventHandler;
    private Thread thread;
    ConnectServer connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /* initialize */
        timer=new CountDownTime(60*1000,1000);

        btn_SignUp=(Button)findViewById(R.id.btn_SignUp);
        btn_getCode=(Button)findViewById(R.id.btn_getCode);

        name=(TextView)findViewById(R.id.edit_UserName);
        phone=(TextView)findViewById(R.id.exit_Phone);
        verification=(TextView)findViewById(R.id.edit_CheckCode);
        passwd=(TextView)findViewById(R.id.edit_Passwd);
        verpasswd=(TextView)findViewById(R.id.edit_verPasswd);

        connect=new ConnectServer();

        MobSDK.init(this,"250a858a8f300","d68d12a22c4e5d8e1f677f92bdc79062");
        /*
         * 添加点击侦听事件（这里用匿名内部类）
         */
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });
        btn_getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGetCode();
            }
        });
        /*
         * MOB内置消息类,afterEvent函数为事件侦听后执行
         * 由于需要验证码验证、调用验证码等多方面处理，所以新建Message类
         */
        eventHandler=new EventHandler(){
            @Override
            public void afterEvent(int event,int result,Object data){
                Message msg=new Message();
                msg.arg1=event;
                msg.arg2=result;
                msg.obj=data;
                handler.sendMessage(msg);
            }

        };
        /* 添加侦听 */
        SMSSDK.registerEventHandler(eventHandler);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


    /**
     * Check for Passwd and confirm password
     * Check for UserName
     * Check for
     * @return true or false
     */
    private boolean onClickSignUp(){
        boolean ret=false;

        /*
         * 验证密码是否符合规范
         */
        if(checkPasswd(passwd.getText().toString().trim())){
            /*
             * 验证确认密码是否符合密码
             */
            if(checkVerPasswd(passwd.getText().toString().trim(),verpasswd.getText().toString().trim())){
                /*
                 * 验证验证码是否一致
                 */
                SMSSDK.submitVerificationCode("86",phone.getText().toString().trim(),verification.getText().toString().trim());

            }else{
                Toast.makeText(SignUpActivity.this,"密码不匹配",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(SignUpActivity.this,"密码格式错误，请输入6-16位字符（数字、字母或小数点）",Toast.LENGTH_SHORT).show();
        }
        return ret;
    }

    /**
     * Check for varification code
     * Check for Phone Number
     * @return true or false
     */
    private boolean onClickGetCode(){
        boolean ret=false;
        /*
         * 验证手机号
         */
        //Toast.makeText(SignUpActivity.this,phone.getText().toString().trim(),Toast.LENGTH_SHORT).show();

        if(checkTel(phone.getText().toString().trim())){
            SMSSDK.getVerificationCode("86",phone.getText().toString().trim());
            timer.start();
        }else{
            //Toast.makeText(SignUpActivity.this,phone.getText().toString().trim(),Toast.LENGTH_SHORT).show();
            Toast.makeText(SignUpActivity.this, "请输入正确格式的手机号", Toast.LENGTH_SHORT).show();
        }
        return ret;
    }

    /**
     * check telephone number format;
     * @param tel : telephone Number
     * @return true or false;
     */

    private boolean checkTel(String tel){
        Pattern p=Pattern.compile("^1[3589][0-9]{9}$");
        Matcher m=p.matcher(tel);
        return m.matches();
    }
    private boolean checkPasswd(String Passwd){
        Pattern p=Pattern.compile("^[a-zA-Z0-9_\\.]{6,16}$");
        Matcher m=p.matcher(Passwd);
        return m.matches();
    }
    private boolean checkVerPasswd(String Passwd,String verPasswd){
        if(Passwd.equals(verPasswd)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 计时器内部类
     */
    class CountDownTime extends CountDownTimer{

        @Override
        public void onFinish() {
            btn_getCode.setClickable(true);
            btn_getCode.setText("获取验证码");
        }

        @Override
        public void onTick(long time){
            btn_getCode.setClickable(false);
            btn_getCode.setText(time/1000+"秒后重试");
        }

        public CountDownTime(long time,long deltatime){
            super(time,deltatime);
        }
    }

    /**
     *
     */
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event=msg.arg1;
            int result=msg.arg2;
            Object data=msg.obj;
            if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                if(result == SMSSDK.RESULT_COMPLETE){
                    boolean smart=(Boolean)data;
                    if(smart){
                        Toast.makeText(getApplicationContext(),"该手机号已经注册过，请重新输入",
                                Toast.LENGTH_LONG).show();
                        phone.requestFocus();
                        return;
                    }
                }
            }
            if(result == SMSSDK.RESULT_COMPLETE){
                if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    Toast.makeText(getApplicationContext(), "验证码输入正确",
                            Toast.LENGTH_LONG).show();
                    Send();
                }
            }else{
                Toast.makeText(getApplicationContext(),"验证码输入错误", Toast.LENGTH_LONG).show();
            }
        }
        public void Send(){
            thread=new Thread(connect);
            thread.start();
            JSONObject json=new JSONObject();
            MessageBox message=null;
            try {
                json.put("Value", "SIGNIN");
                json.put("UserName",name.getText().toString());
                json.put("Passwd",passwd.getText().toString()); //  非加密密码 明文传输需要更改
                json.put("Phone",phone.getText().toString());
                message=connect.Send(json);
                switch (message){
                    case SU_SUCCESS:
                        Toast.makeText(getApplicationContext(), "注册成功",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SU_FAIL:
                        Toast.makeText(getApplicationContext(), "注册失败",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SYS_NETERR:
                        Toast.makeText(getApplicationContext(), "网络连接失败",
                                Toast.LENGTH_LONG).show();
                }
                connect.CloseSocket();
            }catch(JSONException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    };
}

