package com.SillyGoose.Model;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.SillyGoose.Utils.MessageBox;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static android.content.ContentValues.TAG;

/**
 * OkHttp
 * Connect to Server and POST\GET Message
 * Signle Model
 * Created by wangj on 2018/4/19.
 */

public class OkHttpUnits {
    /**
     * 此类单例，防止多次连接等问题
     */
    private static OkHttpUnits httpUnits=null;
    private static MediaType JSON=MediaType.parse("application/json");
    private OkHttpUnits(){
    }
    public static OkHttpUnits getInstance(){
        if(httpUnits == null){
            synchronized (OkHttpUnits.class){
                httpUnits=new OkHttpUnits();
            }
        }
        return httpUnits;
    }
    /*
     * initialize OkHttpClient
     */
    private static OkHttpClient client = null;
    public synchronized static OkHttpClient getClient(){
        if(client == null){
            /* Use builder.build() Initialize client */

            // Cache Dir
            File sdcache=new File(Environment.getExternalStorageDirectory(),"SillyGooseClientCache");
            int cacheSize=10*1024*1024;//10M

            /* Set Intercepter */
            HttpLoggingInterceptor httpLoggingInterceptor;
            httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message){
                    Log.i("Client",message.toString());
                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            /* Initialize */
            client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    //添加OkHttp3的拦截器
                    .addInterceptor(httpLoggingInterceptor)
                    .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
                    .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                    .build();
        }
        return client;
    }
    private static Handler mHandler = null;

    public synchronized static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }

        return mHandler;
    }

    /**
     * Async Get Method
     * @param url
     * @param callback
     * @return
     */
    public static MessageBox get(String url, Callback callback){

        MessageBox messageBox = null;
        OkHttpClient client=getClient();
        Request request = new Request.Builder().url(url).build();
        // asynchronous
        Call call = client.newCall(request);
        call.enqueue(callback);

        return messageBox;
    }

    /**
     * Sync Get Method
     * @param url
     * @return
     */
    public static MessageBox get(String url){
        MessageBox messageBox = null;

        OkHttpClient client=getClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try{
            Response response=call.execute();
            messageBox = MessageBox.valueOf(response.body().toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        return messageBox;
    }

    /**
     * Sync Post Method
     * @param url
     * @param params
     * @return
     */
    public static MessageBox post(String url, JSONObject params) throws IOException{
        MessageBox messageBox = null;
        String postmessage = params.toString();
        RequestBody body = RequestBody.create(JSON,postmessage);
        OkHttpClient client = getClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        String msg=response.body().string();
        Log.d(TAG,msg);
        if(response.isSuccessful()){
            messageBox = MessageBox.valueOf(msg);
        }else{
            messageBox = MessageBox.SYS_NETERR;
        }
        return messageBox;
    }

    public static MessageBox post(String url, JSONObject data,Callback call) throws IOException{
        MessageBox messageBox = null;
        String postmessage = data.toString();

        RequestBody body = RequestBody.create(JSON,postmessage);
        OkHttpClient client = getClient();
        /* 请求 */
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        /* 响应 */
        client.newCall(request).enqueue(call);


        return messageBox;
    }
}
