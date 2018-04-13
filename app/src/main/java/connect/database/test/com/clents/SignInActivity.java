package connect.database.test.com.clents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class SignInActivity extends AppCompatActivity {

    private Button btn_SignIn;
    private Button btn_SignUp;
    private TextView text_Phone;
    private TextView text_Passwd;
    private Thread thread;
    private ConnectServer connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        /* initizalize */
        text_Passwd=(TextView)findViewById(R.id.edit_Passwd);
        text_Phone=(TextView)findViewById(R.id.exit_Phone);
        btn_SignIn=(Button)findViewById(R.id.btn_SignIn);
        btn_SignUp=(Button)findViewById(R.id.btn_SignUp);

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
    private void SignIn(){
        connect=new ConnectServer();
        thread=new Thread(connect);
        thread.start();
        JSONObject data=new JSONObject();
        try{
            data.put("Value","SIGNIN");
            data.put("Phone",text_Phone.getText().toString());
            data.put("Passwd",text_Passwd.getText().toString());
            if((connect.Send(data))==MessageBox.SI_SUCCESS){
                Toast.makeText(getApplicationContext(),"登录成功",
                        Toast.LENGTH_LONG).show();
            }else{

            }
        }catch(JSONException je){
            je.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

}
